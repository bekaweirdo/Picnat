package com.picnat.core.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.picnat.core.base.BaseFeature

interface GlobalNavigatorHandler {

    fun loadFeature(feature : BaseFeature, screen: FragmentScreen, unloadFeatures: Boolean = false)

    fun unloadFeature(feature : BaseFeature)

    fun clearFeatureBackstack()

}