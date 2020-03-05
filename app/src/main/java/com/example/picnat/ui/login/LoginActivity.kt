package com.example.picnat.ui.login

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.PicnatApplication
import com.example.picnat.R
import com.example.picnat.databinding.ActivityLoginBinding
import com.example.picnat.repository.AuthInterface
import com.example.picnat.ui.auth.AuthViewModel
import com.example.picnat.ui.auth.AuthViewModelFactory
import com.example.picnat.utils.startHomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), AuthInterface {
    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var viewModelFactory: AuthViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PicnatApplication).appComponent.inject(this)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[AuthViewModel::class.java]

        binding.viewmodel = viewModel

        viewModel.authListener = this

    }

    override fun onStarted() {
        val animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        groupLoggedIn.startAnimation(animFadeOut)
        progressbar.visibility = VISIBLE
    }

    override fun onSuccess() {
        progressbar.visibility = GONE
        startHomeActivity()
    }

    override fun onFailure(message: String) {
        progressbar.visibility = GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
