package com.picnat

import android.app.Application
import com.picnat.app.koin.navigationModule
import com.picnat.app.koin.roomDataSourceModule
import com.google.firebase.FirebaseApp
import com.picnat.feature_auth.feature.AuthFeature
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class PicnatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@PicnatApplication)
            modules(roomDataSourceModule, navigationModule)
        }
        AuthFeature.init()
    }
}