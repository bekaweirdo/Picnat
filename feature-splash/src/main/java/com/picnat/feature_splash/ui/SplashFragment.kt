package com.picnat.feature_splash.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.picnat.core.base.BaseFragment
import com.picnat.feature_splash.R
import org.koin.android.ext.android.inject

class SplashFragment : BaseFragment<SplashViewModel>() {

    override val viewModel: SplashViewModel by inject()
    override val getLayout: Int
        get() = R.layout.splash_fragment

    private lateinit var splashLogo: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashLogo = view.findViewById(R.id.splashLogo)
        splashLogo.setOnClickListener{
            viewModel.logIn()
        }
    }
}