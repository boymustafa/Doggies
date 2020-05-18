package com.boymustafa.doggies.application

import android.app.Application
import com.boymustafa.doggies.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DogApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Adding Koin modules to our application
        startKoin {
            androidContext(this@DogApplication)
            modules(appModule)
        }
    }
}