package com.example

import android.app.Application
import com.example.picnat.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.picnat.feature_auth.AuthFeature


class PicnatApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PicnatApplication)
            modules(appModule)
        }
        AuthFeature.init()
    }
}