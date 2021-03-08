package com.picnat.feature_auth.ui.sign_up_info

import android.os.Bundle
import android.view.View
import com.picnat.core.base.BaseFragment
import com.picnat.core_components.view.button.RoundedButton
import com.picnat.core_components.view.edit_text.EditTextWithTitle
import com.picnat.feature_auth.R
import com.picnat.feature_auth.data.model.SignUpInfoModel
import org.koin.android.ext.android.inject

class SignUpInfoFragment : BaseFragment<SignUpInfoViewModel>() {

    override val getLayout: Int
        get() = R.layout.sign_up_info_fragment
    override val viewModel: SignUpInfoViewModel by inject()

    private val signUpInfoModel by lazy {
        requireArguments().get(SIGN_UP_INFO_MODEL) as SignUpInfoModel
    }
    private lateinit var usernameEditText: EditTextWithTitle
    private lateinit var firstNameEditText: EditTextWithTitle
    private lateinit var lastNameEditText: EditTextWithTitle
    private lateinit var signUpButton: RoundedButton

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        usernameEditText = view.findViewById(R.id.authSignUpUsername)
        firstNameEditText = view.findViewById(R.id.authSignUpName)
        lastNameEditText = view.findViewById(R.id.authSignUpLastName)
        signUpButton = view.findViewById(R.id.authSignUpInfoButton)

        signUpButton.setOnClickListener {
            viewModel.signUp(
                email = signUpInfoModel.email,
                password = signUpInfoModel.password,
                username = usernameEditText.getInputText(),
                firstName = firstNameEditText.getInputText(),
                lastName = lastNameEditText.getInputText()
            )
        }
    }

    companion object {

        val SIGN_UP_INFO_MODEL: String = this::class.java.simpleName

        fun newInstance(data: Bundle) = SignUpInfoFragment().apply {
            arguments = data
        }
    }
}