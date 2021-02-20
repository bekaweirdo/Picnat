package com.example.picnat.ui.login

import android.os.Bundle
import android.view.View.VISIBLE
import android.view.animation.AnimationUtils
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

    @Inject
    lateinit var viewModelFactory: AuthViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PicnatApplication).appComponent.inject(this)

        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[AuthViewModel::class.java]
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
