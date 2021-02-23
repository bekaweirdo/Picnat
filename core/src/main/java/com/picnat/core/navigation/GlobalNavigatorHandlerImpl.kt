package com.picnat.core.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.picnat.core.base.BaseFeature

class GlobalNavigatorHandlerImpl(private val router: Router) : GlobalNavigatorHandler {

    private val featureBackstack : MutableMap<Int, BaseFeature> = mutableMapOf()

    override fun loadFeature(feature: BaseFeature, screen: FragmentScreen, unloadFeatures : Boolean) {
        feature.loadModules()
        if(unloadFeatures)
            clearFeatureBackstack()
        featureBackstack[featureBackstack.size] = feature
        router.replaceScreen(screen)
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

}