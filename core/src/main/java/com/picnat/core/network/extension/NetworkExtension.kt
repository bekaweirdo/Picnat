package com.picnat.core.network.extension

import com.picnat.core.network.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

suspend fun <T> Flow<ResponseState<T>>.go(
    loading: (() -> Unit)? = null,
    onFinished: (() -> Unit)? = null,
    onSuccess: (() -> Unit)? = null,
    onSuccessWithData: ((data: T) -> Unit)? = null,
    onFailure: (message: String) -> Unit
) {
    this.collect {
        when(it){
            is ResponseState.Loading -> loading?.invoke()
            is ResponseState.SuccessWithData -> {
                onFinished?.invoke()
                onSuccessWithData?.invoke(it.data)
            }
            is ResponseState.Success -> {
                onFinished?.invoke()
                onSuccess?.invoke()
            }
            is ResponseState.Failed -> {
                onFinished?.invoke()
                onFailure.invoke(it.message)
            }
        }
    }
}