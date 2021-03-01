package com.picnat.feature_auth.ui.sign_up

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.picnat.core.base.BaseFragment
import com.picnat.core_components.view.button.RoundedButton
import com.picnat.core_components.view.edit_text.EditTextWithTitle
import com.picnat.feature_auth.R
import com.picnat.feature_auth.feature.AuthFeature
import org.koin.android.ext.android.inject

class SignUpFragment : BaseFragment<SignUpViewModel>() {

    override val getLayout: Int
        get() = R.layout.sign_up_fragment
    override val viewModel: SignUpViewModel by inject()

    private lateinit var emailEditText: EditTextWithTitle
    private lateinit var passwordEditText: EditTextWithTitle
    private lateinit var confirmPasswordEditText: EditTextWithTitle
    private lateinit var signUpButton: RoundedButton
    private lateinit var logInText: TextView

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        emailEditText = view.findViewById(R.id.authSignUpEmail)
        passwordEditText = view.findViewById(R.id.authSignUpPassword)
        confirmPasswordEditText = view.findViewById(R.id.authSignUpPasswordConfirm)
        signUpButton = view.findViewById(R.id.authSignUpButton)
        logInText = view.findViewById(R.id.authSignUpLogIn)

        logInText.setOnClickListener {
            localNavigator.navigateTo(AuthFeature.AuthScreens.authLogIn())
        }
    }

    override fun onBindViewModel(viewModel: SignUpViewModel) {
        super.onBindViewModel(viewModel)
        signUpButton.setOnClickListener {
            viewModel.signUp()
        }
    }
}