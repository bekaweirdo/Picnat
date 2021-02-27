package com.picnat.feature_auth.ui.login

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.picnat.core.base.BaseFragment
import com.picnat.core_components.view.button.RoundedButton
import com.picnat.core.locale.LocaleManager
import com.picnat.core_components.view.button.ImageButton
import com.picnat.core_components.view.edit_text.EditTextWithTitle
import com.picnat.feature_auth.R
import com.picnat.feature_auth.feature.AuthFeature
import org.koin.android.ext.android.inject


class LogInFragment : BaseFragment<LogInViewModel>() {

    override val getLayout: Int
        get() = R.layout.log_in_fragment

    override val viewModel: LogInViewModel by inject()

    private lateinit var emailEditText: EditTextWithTitle
    private lateinit var passwordEditText: EditTextWithTitle
    private lateinit var loginButton : RoundedButton
    private lateinit var signUpText : TextView
    private lateinit var loginButton : RoundedButton
    private lateinit var languageImageView: ImageButton
    private lateinit var languageLabel: TextView
    private var changeLanguageMenu: AlertDialog.Builder? = null

    override fun initViews(view: View) {
        emailEditText = view.findViewById(R.id.authLoginEmail)
        passwordEditText = view.findViewById(R.id.authLoginPassword)
        loginButton = view.findViewById(R.id.authLoginButton)
        signUpText = view.findViewById(R.id.authLogInSignUp)

        signUpText.setOnClickListener {
            localNavigator.navigateTo(AuthFeature.AuthScreens.authSignUp())
        }
        languageImageView = view.findViewById(R.id.languageImageView)
        languageLabel = view.findViewById(R.id.languageLabel)
    }

    override fun onBindViewModel(vm: LogInViewModel) {
        super.onBindViewModel(vm)
        languageImageView.setOnClickListener {
//            changeLanguageMenu?.show()
        }
        loginButton.setOnClickListener {
            val email = emailEditText.getInput()
            val password = passwordEditText.getInput()
            viewModel.login(email, password)
        }
        languageLabel.text = vm.localManager.selectedLanguage.shortName
        languageImageView.visibility = View.VISIBLE
        initLanguageMenu(vm.localManager)
    }

    private fun initLanguageMenu(localManager: LocaleManager) {
//        changeLanguageMenu = AlertDialog.Builder(requireContext())
//            .setTitle("Choose a Language")
//            .setMessage("Hello")
//            .setAdapter()
//        changeLanguageMenu!!.setOnCancelListener {
//            Toast.makeText(context, localManager.selectedLanguage.displayName, Toast.LENGTH_SHORT)
//                .show()
//            activity?.recreate()
//        }
    }
}