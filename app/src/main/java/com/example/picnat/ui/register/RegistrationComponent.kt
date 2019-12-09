package com.example.picnat.ui.register

import dagger.Subcomponent

@Subcomponent
interface RegistrationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }

    fun inject(activity: RegisterActivity)
}