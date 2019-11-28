package com.example.picnat.di.component

import android.content.Context
import com.example.picnat.repository.RegistrationComponent
import com.example.picnat.ui.splash.SplashComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [])
interface AppComponent{

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun splashComponent():SplashComponent.Factory
    fun registrationComponent(): RegistrationComponent.Factory
}