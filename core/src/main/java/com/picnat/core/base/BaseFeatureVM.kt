package com.picnat.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picnat.core.data.resource_provider.ResourceProvider
import com.picnat.core.navigation.api.GlobalNavigator
import com.picnat.core.navigation.api.LocalNavigator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseFeatureVM : ViewModel(), KoinComponent {

    val errorMessage = MutableLiveData<String>()
    val _showLoading = MutableLiveData<Boolean>()

    val resourceProvider : ResourceProvider by inject()

    protected val localNavigator : LocalNavigator by inject()
    protected val globalNavigator : GlobalNavigator by inject()

    protected fun showLoading() = _showLoading.postValue(true)
    protected fun hideLoading() = _showLoading.postValue(false)
}