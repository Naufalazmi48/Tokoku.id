package com.example.tokokuid.core.di

import androidx.room.Room
import com.example.tokokuid.core.data.TokoRepository
import com.example.tokokuid.core.data.source.local.LocalDataSource
import com.example.tokokuid.core.data.source.local.room.TokoDatabase
import com.example.tokokuid.core.data.source.remote.RemoteDataSource
import com.example.tokokuid.core.data.source.remote.network.ApiService
import com.example.tokokuid.core.domain.ITokoRepository
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
        Room.databaseBuilder(
            androidContext(),
            TokoDatabase::class.java, "Toko.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
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
        //factory { AppExecutors() }
        single<ITokoRepository> { TokoRepository(get(),get()) }
    }