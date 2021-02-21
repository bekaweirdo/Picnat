package com.example

import android.app.Application
import com.example.picnat.di.component.AppComponent
import com.example.picnat.di.component.DaggerAppComponent
import com.picnat.feature_auth.AuthFeature


class PicnatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AuthFeature.init()
    }
}