package com.picnat.feature_auth.ui.login

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.picnat.core.base.BaseFragment
import com.picnat.core.locale.Language
import com.picnat.core_components.view.button.RoundedButton
import com.picnat.core.locale.LocaleManager
import com.picnat.core.locale.adapter.LanguageAdapter
import com.picnat.core_components.menu.BottomMenuDialog
import com.picnat.core_components.view.button.ImageButton
import com.picnat.core_components.view.edit_text.EditTextWithTitle
import com.picnat.feature_auth.R
import com.picnat.feature_auth.feature.AuthFeature
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class LogInFragment : BaseFragment<LogInViewModel>() {

    override val getLayout: Int
        get() = R.layout.log_in_fragment

    override val viewModel: LogInViewModel by viewModel()

    private lateinit var emailEditText: EditTextWithTitle
    private lateinit var passwordEditText: EditTextWithTitle
    private lateinit var signUpText : TextView
    private lateinit var loginButton : RoundedButton
    private lateinit var languageImageView: ImageButton
    private lateinit var languageLabel: TextView
    private var changeLanguageMenu: BottomMenuDialog? = null

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        emailEditText = view.findViewById(R.id.authLoginEmail)
        passwordEditText = view.findViewById(R.id.authLoginPassword)
        loginButton = view.findViewById(R.id.authLoginButton)
        signUpText = view.findViewById(R.id.authLogInSignUp)

        signUpText.setOnClickListener {
            localNavigator.replaceScreen(AuthFeature.AuthScreens.authSignUp())
        }
        languageImageView = view.findViewById(R.id.languageImageView)
        languageLabel = view.findViewById(R.id.languageLabel)
    }

    override fun onBindViewModel(vm: LogInViewModel) {
        super.onBindViewModel(vm)
        languageImageView.setOnClickListener {
            changeLanguageMenu?.show()
        }
        loginButton.setOnClickListener {
            val email = emailEditText.getInputText()
            val password = passwordEditText.getInputText()
            viewModel.login(email, password)
        }
        languageLabel.text = vm.localManager.selectedLanguage.shortName
        languageImageView.visibility = View.VISIBLE
        initLanguageMenu(vm.localManager)
    }

    private fun initLanguageMenu(localManager: LocaleManager) {
        changeLanguageMenu = BottomMenuDialog(requireContext())
        changeLanguageMenu?.setMenuMessage("Select a Language")
        changeLanguageMenu?.menuTopViewEnable(isEnable = false)
        changeLanguageMenu?.setAdapter(
            LanguageAdapter(
                localManager.languageOptions,
                localManager.selectedLanguage
            )
        )
        changeLanguageMenu?.listener(menuListener = {position, language ->
            if (position != -1) {
                viewModel.changeLanguage(language as Language)
                activity?.recreate()
            }
        })
    }
}