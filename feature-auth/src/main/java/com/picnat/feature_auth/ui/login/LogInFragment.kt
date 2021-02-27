package com.picnat.feature_auth.ui.login

import android.view.View
import android.widget.Button
import android.widget.Toast
import com.picnat.core.base.BaseFragment
import com.picnat.core_components.view.edit_text.EditTextWithTitle
import com.picnat.feature_auth.R
import org.koin.android.ext.android.inject


class LogInFragment : BaseFragment<LogInViewModel>() {

    override val getLayout: Int
        get() = R.layout.log_in_fragment

    override val viewModel: LogInViewModel by inject()

    private lateinit var emailEditText: EditTextWithTitle
    private lateinit var passwordEditText: EditTextWithTitle
    private lateinit var loginButton : Button

    override fun initViews(view: View) {
        emailEditText = view.findViewById(R.id.authLoginEmail)
        passwordEditText = view.findViewById(R.id.authLoginPassword)
        loginButton = view.findViewById(R.id.authLoginButton)
    }

    override fun onBindViewModel(viewModel: LogInViewModel) {
        super.onBindViewModel(viewModel)
        loginButton.setOnClickListener {
            val email = emailEditText.getInput()
            val password = passwordEditText.getInput()
            if (email.isBlank() || password.isBlank())
                Toast.makeText(requireContext(), "Email or password is blank!", Toast.LENGTH_SHORT)
                    .show()
            else
                viewModel.login(email, password)
        }
    }
}