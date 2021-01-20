package com.example.tokokuid.core.di

import androidx.room.Room
import com.example.tokokuid.core.data.TokoRepository
import com.example.tokokuid.core.data.source.local.LocalDataSource
import com.example.tokokuid.core.data.source.local.room.TokoDatabase
import com.example.tokokuid.core.data.source.remote.RemoteDataSource
import com.example.tokokuid.core.data.source.remote.network.ApiService
import com.example.tokokuid.core.domain.ITokoRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<TokoDatabase>().tokoDAO() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("tokoku".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            TokoDatabase::class.java, "Toko.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "com.example.tokokuid"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/wRcDgNNQITam2EFAsjLQoD9vfnSvVVej8qL9DdBVvjI=")
            .add(hostname, "sha256/YLh1dUR9y6Kja30RrAn7JKnbQG/uEtLMkBgFF2Fuihg=")
            .add(hostname, "sha256/Vjs8r4z+80wjNcr1YKepWQboSIRi63WsWXhIMN+eWys=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rajaongkir.com/starter/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
    val repositoryModule = module {
        single { LocalDataSource(get()) }
        single { RemoteDataSource(get()) }
        single<ITokoRepository> { TokoRepository(get(),get()) }
    }