package com.example.tokokuid.core.di

import androidx.room.Room
import com.example.tokokuid.core.data.TokoRepository
import com.example.tokokuid.core.data.source.local.LocalDataSource
import com.example.tokokuid.core.data.source.local.room.TokoDatabase
import com.example.tokokuid.core.domain.ITokoRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<TokoDatabase>().tokoDAO() }
    single {
        Room.databaseBuilder(
            androidContext(),
            TokoDatabase::class.java, "Toko.db"
        ).fallbackToDestructiveMigration().build()
    }
}
    val repositoryModule = module {
        single { LocalDataSource(get()) }
        //single { RemoteDataSource(get()) }
        //factory { AppExecutors() }
        single<ITokoRepository> { TokoRepository(get()) }
    }