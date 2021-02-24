//package com.picnat.feature_auth.feature.ui
//
//import android.os.Bundle
//import com.picnat.core.base.BaseFeatureFragment
//import com.picnat.core.navigation.LocalNavigatorHandler
//import com.picnat.feature_auth.feature.AuthScreens
//import org.koin.android.ext.android.inject
//
//abstract class AuthFeatureFragment<VM : AuthFeatureVM>: BaseFeatureFragment<VM>() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        localNavigator.replaceScreen(AuthScreens.LogIn())
//    }
//
//    protected val localNavigator : LocalNavigatorHandler by inject()
//}