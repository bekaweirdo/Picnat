package com.picnat.core.network.extension

import com.picnat.core.network.ResponseState

fun <T> ResponseState<T>.go(
    loading: (() -> Unit)? = null,
    onFinished: (() -> Unit)? = null,
    onSuccess: (() -> Unit)? = null,
    onSuccessWithData: ((data: T) -> Unit)? = null,
    onFailure: (message: String) -> Unit
) {
    loading?.invoke()
    when (this) {
        is ResponseState.Loading -> loading?.invoke()

        is ResponseState.SuccessWithData -> {
            onFinished?.invoke()
            onSuccessWithData?.invoke(this.data)
        }
        is ResponseState.Success -> {
            onFinished?.invoke()
            onSuccess?.invoke()
        }
        is ResponseState.Failed -> {
            onFinished?.invoke()
            onFailure.invoke(message)
        }
    }
}