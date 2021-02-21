package com.picnat.core.network.extension

import com.picnat.core.network.ResponseState

fun <T> ResponseState<T>.go(
    loading: (() -> Unit)? = null,
    success: (data: T) -> Unit,
    failure: (message: String) -> Unit
) {
    when (this) {
        is ResponseState.Loading -> loading?.invoke()
        is ResponseState.Success -> success(this.data)
        is ResponseState.Failed -> failure(message)
    }
}