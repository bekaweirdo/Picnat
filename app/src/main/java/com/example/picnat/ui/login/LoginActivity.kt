package com.example.picnat.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.picnat.R
import com.example.picnat.databinding.ActivityLoginBinding
import com.example.picnat.repository.AuthInterface
import com.example.picnat.repository.UserRepository
import com.example.picnat.ui.auth.AuthViewModel
import com.example.picnat.ui.auth.AuthViewModelFactory
import com.example.picnat.utils.startHomeActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthInterface {
    private lateinit var viewModel: AuthViewModel

    private val factory: AuthViewModelFactory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewModel = ViewModelProviders.of(this,factory)[AuthViewModel::class.java]
        binding.viewmodel = viewModel

        viewModel.authListener = this

    }

    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
        startHomeActivity()
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        viewModel.user?.let{
            startHomeActivity()
        }
    }

}
