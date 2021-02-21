package com.example

import android.app.Application
import com.example.picnat.di.module.roomPicnatApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.picnat.feature_auth.AuthFeature


class PicnatApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PicnatApplication)
            modules(roomPicnatApp)
        }
        AuthFeature.init()
    }
}