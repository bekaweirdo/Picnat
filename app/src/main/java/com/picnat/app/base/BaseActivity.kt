package com.picnat.app.base

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.picnat.app.PicnatAppNavigator
import com.picnat.app.R
import com.picnat.core.navigation.GlobalNavigatorHandlerImpl
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseActivity : FragmentActivity() {

    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator : PicnatAppNavigator by inject { parametersOf(this, R.id.container) }
    protected val navigatorHandler : GlobalNavigatorHandlerImpl by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}