package com.picnat.core.base

import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

interface BaseFeature {
    //val featureFragment : KClass<out BaseFeatureFragment<*>>
    val modulesList : List<Module>
    fun loadModules() = loadKoinModules(modulesList)
    fun unloadModules() = unloadKoinModules(modulesList)
}