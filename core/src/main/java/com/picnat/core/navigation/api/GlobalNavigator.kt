package com.picnat.core.navigation.api

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.picnat.core.base.BaseFeature

interface GlobalNavigator {

    fun loadFeature(feature : BaseFeature, screen: FragmentScreen, unloadFeatures: Boolean = false)

    fun unloadFeature(feature : BaseFeature)

    fun clearFeatureBackstack()

    fun loadSplashFeature()

    fun loadAuthFeature()

}