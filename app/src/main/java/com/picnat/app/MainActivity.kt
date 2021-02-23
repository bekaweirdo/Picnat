package com.picnat.app

import android.os.Bundle
import com.picnat.app.base.BaseActivity
import com.picnat.feature_auth.feature.AuthFeature
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigatorHandler.loadFeature(AuthFeature, AuthFeature.AuthScreens.authLogIn())
    }

}
