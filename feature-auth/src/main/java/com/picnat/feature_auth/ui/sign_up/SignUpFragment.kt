package com.picnat.feature_auth.ui.sign_up

import android.view.View
import com.picnat.core.base.BaseFragment
import com.picnat.feature_auth.R
import org.koin.android.ext.android.inject

class SignUpFragment : BaseFragment<SignUpViewModel>() {

    override val getLayout: Int
        get() = R.layout.sign_up_fragment

    override val viewModel: SignUpViewModel by inject()

    override fun initViews(view: View) {}

    override fun onBindViewModel(vm: SignUpViewModel) {
        super.onBindViewModel(vm)

    }
}