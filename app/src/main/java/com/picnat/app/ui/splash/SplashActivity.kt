//package com.example.picnat.ui.splash
//
//import android.content.Intent
//import android.graphics.drawable.AnimatedVectorDrawable
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import androidx.viewpager2.widget.ViewPager2
//import com.example.picnat.R
//import com.example.picnat.data.model.PagerM
//import com.example.picnat.managers.SharedPreferencesManager
//import com.example.picnat.ui.login.LoginActivity
//import kotlinx.android.synthetic.main.activity_splash.*
//import javax.inject.Inject
//
//class SplashActivity : AppCompatActivity() {
//
//    @Inject
//    lateinit var sharedPreferences: SharedPreferencesManager
//    private lateinit var splashViewPager: com.example.picnat.ui.splash.fragments.PagerAdapter
//    private lateinit var animation: AnimatedVectorDrawable
//    private var isLastPageSwiped: Boolean = false
//    private var counterPageScroll: Int = 0
//    override fun onCreate(savedInstanceState: Bundle?) {
//        sharedPreferences = SharedPreferencesManager(this)
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//        setupViewPager()
//        btn_skip.setOnClickListener {
//            launchHomeScreen()
//        }
//
//      //  (application as PicnatApplication).appComponent.splashComponent().create().inject(this)
//
//        checkSplashPage()
//        btn_next.setOnClickListener {
//            // val current
//            //TODO REFACTOR
//            launchHomeScreen()
//        }
//
//    }
//
//    private fun launchHomeScreen(){
//        sharedPreferences.setFirstTimeLaunch(false)
//        val loginActivity = Intent(this,LoginActivity::class.java)
//        startActivity(loginActivity)
//        finish()
//    }
//    private fun checkSplashPage(){
//        if(!sharedPreferences.isFirstTimeLaunch()){
//            val loginActivity = Intent(this,LoginActivity::class.java)
//            startActivity(loginActivity)
//            finish()
//        }
//    }
//
//    private fun setupViewPager(){
//        splashViewPager = com.example.picnat.ui.splash.fragments.PagerAdapter(fetchData())
//        viewPager2.adapter = splashViewPager
//        viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL
//
//        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//                if(position == 1){
//                        isLastPageSwiped=true
//                        btn_skip.visibility = View.GONE
//                        btn_arrow.visibility = View.GONE
//                        animation = btn_next.drawable as AnimatedVectorDrawable
//                        animation.start()
//
//            }
//        }
//        })
//    }
//
//    private fun fetchData(): ArrayList<PagerM>{
//        val data = resources.getStringArray(R.array.welcome_array)
//        var layouts: ArrayList<PagerM> = ArrayList()
//        for(str in data){
//            layouts.add(PagerM(str))
//        }
//        return layouts
//    }
//}
