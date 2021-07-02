package com.muramsyah.mygithubusers

import android.app.Application
import com.muramsyah.mygithubusers.core.di.databaseModule
import com.muramsyah.mygithubusers.core.di.networkModule
import com.muramsyah.mygithubusers.core.di.repositoryModule
import com.muramsyah.mygithubusers.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule
                )
            )
        }
    }
}