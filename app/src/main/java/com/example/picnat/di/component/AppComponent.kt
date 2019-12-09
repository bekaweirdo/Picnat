package com.example.picnat.di.component

import android.content.Context
import com.example.picnat.di.module.AppModule
import com.example.picnat.ui.home.HomeFragment
import com.example.picnat.ui.register.RegistrationComponent
import com.example.picnat.ui.login.LoginActivity
import com.example.picnat.ui.splash.SplashComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun splashComponent(): SplashComponent.Factory
    fun registrationComponent(): RegistrationComponent.Factory

    fun inject(loginActivity: LoginActivity)
    fun inject(homeFragment: HomeFragment)
}