package com.example

import android.app.Application
import com.example.picnat.koin.navigationModule
import com.example.picnat.koin.roomDataSourceModule
import com.picnat.feature_auth.feature.AuthFeature
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class PicnatApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PicnatApplication)
            modules(roomDataSourceModule, navigationModule)
        }
        AuthFeature.init()
    }
}