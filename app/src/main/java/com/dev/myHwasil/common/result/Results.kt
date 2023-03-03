package com.dev.myHwasil.common.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Results<out T> {
    data class Success<T>(val data: T) : Results<T>

    data class Error(val exception: Throwable? = null) :
        Results<Nothing>

    object Loading : Results<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Results<T>> {
    return this.map<T, Results<T>> {
        Results.Success(it)
    }.onStart {
        emit(Results.Loading)
    }.catch {
        emit(Results.Error(it))
    }
}