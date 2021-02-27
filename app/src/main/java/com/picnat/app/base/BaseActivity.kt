package com.picnat.app.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.github.terrakok.cicerone.NavigatorHolder
import com.picnat.app.PicnatAppNavigator
import com.picnat.app.R
import com.picnat.core.base.BaseFragment
import com.picnat.core.navigation.impl.GlobalNavigatorImpl
import com.picnat.feature_auth.feature.AuthFeature
import com.picnat.feature_splash.feature.SplashFeature
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseActivity : AppCompatActivity() {

    protected val globalNavigator : GlobalNavigatorImpl by inject()

    private lateinit var loadingLayout : FrameLayout
    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator : PicnatAppNavigator by inject { parametersOf(this, R.id.container) }
    private var mLocalBroadcastManager: LocalBroadcastManager? = null
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when(intent.getStringExtra(GlobalNavigatorImpl.FEATURE)){
                GlobalNavigatorImpl.SPLASH_FEATURE -> globalNavigator.loadFeature(SplashFeature, SplashFeature.SplashScreens.splashScreen())
                GlobalNavigatorImpl.AUTH_FEATURE -> globalNavigator.loadFeature(AuthFeature, AuthFeature.AuthScreens.authLogIn(), true)
            }
            if(intent.getBooleanExtra(BaseFragment.LOADING, false))
                showLoading()
            else
                hideLoading()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        registerBroadcast()
        setContentView(R.layout.activity_base)
        loadingLayout = findViewById(R.id.loadingLayout)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        mLocalBroadcastManager?.unregisterReceiver(broadcastReceiver)
        super.onDestroy()
    }

    private fun registerBroadcast() {
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(applicationContext)
        val mIntentFilter = IntentFilter()
        mIntentFilter.addAction(GlobalNavigatorImpl.LOAD_FEATURE)
        mIntentFilter.addAction(BaseFragment.LOADING)
        mLocalBroadcastManager?.registerReceiver(
            broadcastReceiver,
            mIntentFilter
        )
    }

    private fun showLoading() {
        loadingLayout.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        loadingLayout.visibility = View.GONE
    }
}