package com.picnat.feature_splash.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.picnat.core.base.BaseFragment
import com.picnat.feature_splash.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<SplashViewModel>() {

    override val getLayout: Int
        get() = R.layout.splash_fragment
    override val viewModel: SplashViewModel by viewModel()

    private lateinit var splashLogo: ImageView

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        splashLogo = view.findViewById(R.id.splashLogo)
        splashLogo.setOnClickListener{
            viewModel.logIn()
        }
    }
}