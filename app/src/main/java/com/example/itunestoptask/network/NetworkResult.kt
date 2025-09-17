package com.example.itunestoptask.network

sealed class NetworkResult<T : Any> {
    class Success<T : Any>(
        val data: T,
    ) : NetworkResult<T>()

    class Error<T : Any>() : NetworkResult<T>()
}

inline fun <T : Any> NetworkResult<T>.onSuccess(action: (T) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Success) {
        action(this.data)
    }
    return this
}

inline fun <T : Any> NetworkResult<T>.onFailure(action: (Unit) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Error) {
        action(Unit)
    }
    return this
}
