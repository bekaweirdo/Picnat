package com.picnat

import android.app.Application
import com.picnat.app.di.navigationModule
import com.picnat.app.di.roomDataSourceModule
import com.google.firebase.FirebaseApp
import com.picnat.app.di.appModule
import com.picnat.app.di.coroutineDispatcherProviderModule
import com.picnat.core.App
import com.picnat.app.di.localeManagerModule
import com.picnat.app.di.sharedPreference
import com.picnat.core.navigation.api.GlobalNavigator
import com.picnat.feature_splash.feature.SplashFeature
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class PicnatApplication : Application() {
    private val globalNavigator : GlobalNavigator by inject()
    override fun onCreate() {
        super.onCreate()
        App.appContext = applicationContext
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@PicnatApplication)
            modules(appModule, roomDataSourceModule, coroutineDispatcherProviderModule, navigationModule, sharedPreference, localeManagerModule)
        }
        globalNavigator.loadFeature(SplashFeature, SplashFeature.SplashScreens.splashScreen())
    }
}