package com.picnat.core.base

import androidx.lifecycle.ViewModel
import com.picnat.core.navigation.impl.GlobalNavigatorImpl
import com.picnat.core.navigation.impl.LocalNavigatorImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseFeatureVM : ViewModel(), KoinComponent {
    protected val localNavigator : LocalNavigatorImpl by inject()
    protected val globalNavigator : GlobalNavigatorImpl by inject()
}