package com.example.movies.data.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend () -> T
): Resource<T> {
    return withContext(dispatcher) {
        try {
            Resource.Success(data = block.invoke())
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }
}

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : Resource<T>(data)

    class Error<T>(message: String?) : Resource<T>(message = message)

    class Loading<T> : Resource<T>()

}