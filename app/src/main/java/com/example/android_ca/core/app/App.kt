package com.example.android_ca.core.app

import android.app.Application
import com.example.android_ca.core.di.injector
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@App)
            modules(injector)
        }
    }
}