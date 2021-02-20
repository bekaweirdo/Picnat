package com.example.picnat.ui.login

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.PicnatApplication
import com.example.picnat.R
import com.example.picnat.ui.auth.AuthViewModel
import com.example.picnat.ui.auth.AuthViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModel
    private lateinit var emailEditText : EditText
    private lateinit var passwordEditText : EditText


    @Inject
    lateinit var viewModelFactory: AuthViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PicnatApplication).appComponent.inject(this)

        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.loginEmailEditText)
        passwordEditText = findViewById(R.id.loginPasswordEditText)


        viewModel = ViewModelProviders.of(this, viewModelFactory)[AuthViewModel::class.java]
        loginButton.setOnClickListener{
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email.isBlank() || password.isBlank())
                Toast.makeText(baseContext, "Email or password is blank!", Toast.LENGTH_SHORT).show()
            else
                viewModel.login(email, password)
        }
    }

//    override fun onStarted() {
//        val animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
//        groupLoggedIn.startAnimation(animFadeOut)
//        progressbar.visibility = VISIBLE
//    }
//
//    override fun onSuccess() {
//        progressbar.visibility = GONE
//        startHomeActivity()
//    }
//
//    override fun onFailure(message: String) {
//        progressbar.visibility = GONE
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }

}
