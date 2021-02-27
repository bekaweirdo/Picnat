package com.picnat.core.base.coroutines

import kotlinx.coroutines.CoroutineDispatcher

open class CoroutineDispatcherProvider(
    val ui: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val io: CoroutineDispatcher
)