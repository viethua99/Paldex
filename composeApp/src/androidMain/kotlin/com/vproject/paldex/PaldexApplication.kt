package com.vproject.paldex

import android.app.Application
import com.vproject.paldex.di.initKoin
import org.koin.android.ext.koin.androidContext

class PaldexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(applicationContext)
        }
    }
}
