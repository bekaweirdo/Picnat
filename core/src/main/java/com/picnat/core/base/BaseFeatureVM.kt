package com.picnat.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picnat.core.data.resource_provider.ResourceProvider
import com.picnat.core.navigation.impl.GlobalNavigatorImpl
import com.picnat.core.navigation.impl.LocalNavigatorImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseFeatureVM : ViewModel(), KoinComponent {

    val errorMessage = MutableLiveData<String>()

    val resourceProvider : ResourceProvider by inject()

    protected val localNavigator : LocalNavigatorImpl by inject()
    protected val globalNavigator : GlobalNavigatorImpl by inject()
}