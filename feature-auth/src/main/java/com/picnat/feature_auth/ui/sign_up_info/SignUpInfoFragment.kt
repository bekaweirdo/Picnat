package com.picnat.feature_auth.ui.sign_up_info

import android.os.Bundle
import android.view.View
import com.picnat.core.base.BaseFragment
import com.picnat.feature_auth.R
import com.picnat.feature_auth.data.model.SignUpInfoModel
import com.picnat.feature_auth.ui.sign_up.SignUpFragment
import org.koin.android.ext.android.inject

class SignUpInfoFragment : BaseFragment<SignUpInfoViewModel>() {

    override val getLayout: Int
        get() = R.layout.sign_up_info_fragment
    override val viewModel: SignUpInfoViewModel by inject()

    private val signUpInfoModel = requireArguments().get(SIGN_UP_INFO_MODEL) as SignUpInfoModel

    override fun initViews(view: View) {
    }

    companion object {

        val SIGN_UP_INFO_MODEL: String = this::class.java.simpleName

        fun newInstance(data: Bundle) = SignUpFragment().apply {
            arguments = data
        }
    }
}