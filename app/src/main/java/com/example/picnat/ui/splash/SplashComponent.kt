package com.example.picnat.ui.splash

import dagger.Subcomponent


@Subcomponent
interface SplashComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(): SplashComponent
    }

    fun inject(activity: SplashActivity)
}