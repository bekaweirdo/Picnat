package com.example

import android.app.Application
import com.example.picnat.di.component.AppComponent


class PicnatApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}