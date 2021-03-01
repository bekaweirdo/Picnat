package com.picnat.core.base.use_case

import com.picnat.core.base.coroutines.CoroutineDispatcherProvider
import com.picnat.core.network.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * An interactor (use case in Clean Architecture) represents an execution unit of asynchronous work.
 * A [FlowInteractor] exposes a cold stream of values implemented with Kotlin [Flow].
 *
 * Work will be executed on thread as specified by the [dispatcher] of the interactor.
 * code belongs to https://github.com/etman55
 */
abstract class FlowInteractor<in Params, T>(private val dispatcher: CoroutineDispatcherProvider) {

    /**
     * Create a [Flow] for this interactor.
     */
    protected abstract suspend fun execute(params: Params? = null): Flow<ResponseState<T>>

    /**
     * Build a new [Flow] from this interactor.
     */
    suspend operator fun invoke(params: Params? = null): Flow<ResponseState<T>> = execute(params).flowOn(dispatcher.io)
}