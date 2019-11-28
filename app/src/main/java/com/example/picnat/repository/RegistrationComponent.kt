package com.example.picnat.repository

import com.example.picnat.ui.register.RegisterActivity
import dagger.Subcomponent

@Subcomponent
interface RegistrationComponent{

    @Subcomponent.Factory
    interface Factory{
        fun create(): RegistrationComponent
    }

    fun inject(activity: RegisterActivity)
}