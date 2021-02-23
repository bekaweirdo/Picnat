package com.picnat.feature_auth.ui.login

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.picnat.core.base.BaseFragment
import com.picnat.feature_auth.R
import org.koin.android.ext.android.inject


class LogInFragment : BaseFragment<LogInViewModel>() {

    override val getLayout: Int
        get() = R.layout.log_in_fragment

    override val viewModel: LogInViewModel by inject()

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton : Button

    override fun initViews(view: View) {
        emailEditText = view.findViewById(R.id.loginEmailEditText)
        passwordEditText = view.findViewById(R.id.loginPasswordEditText)
        loginButton = view.findViewById(R.id.loginButton)
    }

    override fun onBindViewModel(viewModel: LogInViewModel) {
        super.onBindViewModel(viewModel)
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email.isBlank() || password.isBlank())
                Toast.makeText(requireContext(), "Email or password is blank!", Toast.LENGTH_SHORT)
                    .show()
            else
                viewModel.login(email, password)
        }
    }
}