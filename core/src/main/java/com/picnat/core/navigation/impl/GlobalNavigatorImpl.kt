package com.picnat.core.navigation.impl

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.picnat.core.base.BaseFeature
import com.picnat.core.navigation.api.GlobalNavigator

class GlobalNavigatorImpl(private val router: Router, private val context : Context) : GlobalNavigator {

    companion object{
        const val LOAD_FEATURE = "LOAD_FEATURE"
        const val FEATURE = "FEATURE"
        const val SPLASH_FEATURE = "SPLASH_FEATURE"
        const val AUTH_FEATURE = "AUTH_FEATURE"
    }

    private val featureBackstack : MutableMap<Int, BaseFeature> = mutableMapOf()

    override fun loadFeature(feature: BaseFeature, screen: FragmentScreen, unloadFeatures : Boolean) {
        feature.loadModules()
        if(unloadFeatures) {
            clearFeatureBackstack()
            router.replaceScreen(screen)
        }
        else
        router.newChain(screen)
        featureBackstack[featureBackstack.size] = feature
    }

    override fun unloadFeature(feature: BaseFeature) {
        feature.unloadModules()
        featureBackstack.forEach {
            if(it.value == feature){
                featureBackstack.remove(it.key)
                return@forEach
            }
        }
    }

    override fun clearFeatureBackstack() {
        featureBackstack.forEach {
            it.value.unloadModules()
        }
        featureBackstack.clear()
    }

    override fun loadSplashFeature() = sendLoadFeatureBroadcast(SPLASH_FEATURE)

    override fun loadAuthFeature() = sendLoadFeatureBroadcast(AUTH_FEATURE)

    private fun sendLoadFeatureBroadcast(feature: String){
        val intent = Intent(LOAD_FEATURE)
        intent.putExtra(FEATURE, feature)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }
}