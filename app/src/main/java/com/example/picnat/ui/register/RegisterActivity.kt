package com.example.picnat.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.PicnatApplication
import com.example.picnat.ui.home.MainActivity
import com.example.picnat.R
import com.example.picnat.databinding.ActivityRegisterBinding
import com.example.picnat.repository.AuthInterface
import com.example.picnat.repository.UserRepository
import com.example.picnat.ui.auth.AuthViewModel
import com.example.picnat.ui.auth.AuthViewModelFactory
import com.example.picnat.utils.startHomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class RegisterActivity : AppCompatActivity(),AuthInterface {

    private lateinit var viewModel: AuthViewModel

    private val factory: AuthViewModelFactory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding :ActivityRegisterBinding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        viewModel = ViewModelProviders.of(this,factory)[AuthViewModel::class.java]
        binding.viewmodel = viewModel

        viewModel.authListener = this
        (application as PicnatApplication).appComponent.registrationComponent().create().inject(this)

    }

    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
        Intent(this, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(it)
        }
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
        startHomeActivity()
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}
