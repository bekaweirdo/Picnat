package com.picnat.core.network

sealed class ResponseState<T> {
    class Loading<T> : ResponseState<T>()
    class Success<T> : ResponseState<T>()
    data class SuccessWithData<T>(val data: T) : ResponseState<T>()
    data class Failed<T>(val message: String) : ResponseState<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success() = Success<T>()
        fun <T> successWithData(data: T) = SuccessWithData(data)
        fun <T> failed(message: String) = Failed<T>(message)
    }
}