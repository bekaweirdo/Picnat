package com.example.picnat.ui.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.picnat.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bindUI()
    }


    private fun bindUI(){


        btlogin.setOnClickListener {
            val email = tvemail.text.toString()
            Toast.makeText(this,email,Toast.LENGTH_SHORT).show()
        }
    }
}
