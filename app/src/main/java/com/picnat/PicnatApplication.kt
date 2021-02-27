package com.picnat

import android.app.Application
import com.picnat.app.di.navigationModule
import com.picnat.app.di.roomDataSourceModule
import com.google.firebase.FirebaseApp
import com.picnat.core.App
import com.picnat.app.di.localeManagerModule
import com.picnat.app.di.sharedPreference
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class PicnatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        App.appContext = applicationContext
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@PicnatApplication)
            modules(roomDataSourceModule, navigationModule, sharedPreference, localeManagerModule)
        }
    }
}