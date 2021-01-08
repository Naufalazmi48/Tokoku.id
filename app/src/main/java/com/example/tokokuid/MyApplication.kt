package com.example.tokokuid

import android.app.Application
import com.example.tokokuid.core.di.databaseModule
import com.example.tokokuid.di.useCaseModule
import com.example.tokokuid.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import com.example.tokokuid.core.di.repositoryModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    useCaseModule,
                    viewModelModule,
                    repositoryModule
                )
            )
        }
    }
}