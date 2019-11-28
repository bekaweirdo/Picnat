package com.example.picnat.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.picnat.R
import com.example.picnat.managers.SharedPreferencesManager
import com.example.picnat.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = SharedPreferencesManager(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
      //  (application as PicnatApplication).appComponent.splashComponent().create().inject(this)


        Glide
            .with(applicationContext)
            .load(R.drawable.background_picnat)
            .into(picnat_background)

        checkSplashPage()
        Log.d("pasuxi",sharedPreferences.getSplashLauncher().toString())
        btn_next.setOnClickListener {
            sharedPreferences.saveSplashLauncher()
            val loginActivity = Intent(this,LoginActivity::class.java)
            startActivity(loginActivity)
            finish()
        }
    }

    private fun checkSplashPage(){
        if(sharedPreferences.getSplashLauncher().toString() == "1"){
            Log.d("pasuxi",sharedPreferences.getSplashLauncher().toString())
            val loginActivity = Intent(this,LoginActivity::class.java)
            startActivity(loginActivity)
            finish()
        }
    }
}
