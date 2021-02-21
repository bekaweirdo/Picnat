package com.picnat.feature_auth.feature.fragment

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.picnat.core.base.BaseFragment

abstract class AuthFeatureFragment : BaseFragment<ViewModelProvider> {
    override fun onCreate(savedInstanceState: Bundle?) {
        (Application as Picnat)
    }
}