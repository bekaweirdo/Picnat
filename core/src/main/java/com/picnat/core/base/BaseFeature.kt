package com.picnat.core.base

interface BaseFeature {
    //val featureFragment : KClass<out BaseFeatureFragment<*>>
    fun loadModules()
    fun unloadModules()
}