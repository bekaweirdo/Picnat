package com.example.picnat.ui.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.PicnatApplication
import com.example.picnat.R
import com.example.picnat.ui.auth.AuthViewModel
import com.example.picnat.ui.auth.AuthViewModelFactory
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var viewModelFactory: AuthViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PicnatApplication).appComponent.registrationComponent().create()
            .inject(this)

        setContentView(R.layout.activity_register)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[AuthViewModel::class.java]
    }

//    override fun onStarted() {
//        progressbar.visibility = View.VISIBLE
//        Intent(this, MainActivity::class.java).also {
//            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(it)
//        }
//    }
//
//    override fun onSuccess() {
//        progressbar.visibility = View.GONE
//        startHomeActivity()
//    }
//
//    override fun onFailure(message: String) {
//        progressbar.visibility = View.GONE
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
}
