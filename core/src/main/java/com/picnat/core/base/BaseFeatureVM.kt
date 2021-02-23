package com.picnat.core.base

import androidx.lifecycle.ViewModel
import com.picnat.core.navigation.GlobalNavigatorHandler
import com.picnat.core.navigation.LocalNavigatorHandlerImpl
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
abstract class BaseFeatureVM : ViewModel(), KoinComponent {
    protected val localNavigator : LocalNavigatorHandlerImpl by inject()
    protected val globalNavigator : GlobalNavigatorHandler by inject()
}