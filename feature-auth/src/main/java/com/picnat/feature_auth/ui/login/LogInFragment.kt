package com.picnat.feature_auth.ui.login

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.picnat.core.base.BaseFragment
import com.picnat.core_components.view.button.RoundedButton
import com.picnat.core_components.view.edit_text.EditTextWithTitle
import com.picnat.feature_auth.R
import com.picnat.feature_auth.feature.AuthFeature
import org.koin.androidx.viewmodel.ext.android.viewModel


class LogInFragment : BaseFragment<LogInViewModel>() {

    override val getLayout: Int
        get() = R.layout.log_in_fragment

    override val viewModel: LogInViewModel by viewModel()

    private lateinit var emailEditText: EditTextWithTitle
    private lateinit var passwordEditText: EditTextWithTitle
    private lateinit var loginButton : RoundedButton
    private lateinit var signUpText : TextView

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        emailEditText = view.findViewById(R.id.authLoginEmail)
        passwordEditText = view.findViewById(R.id.authLoginPassword)
        loginButton = view.findViewById(R.id.authLoginButton)
        signUpText = view.findViewById(R.id.authLogInSignUp)

        signUpText.setOnClickListener {
            localNavigator.replaceScreen(AuthFeature.AuthScreens.authSignUp())
        }
    }

    override fun onBindViewModel(viewModel: LogInViewModel) {
        super.onBindViewModel(viewModel)
        loginButton.setOnClickListener {
            val email = emailEditText.getInputText()
            val password = passwordEditText.getInputText()
            viewModel.login(email, password)
        }
    }
}