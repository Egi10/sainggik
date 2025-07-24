package id.buaja.sainggik.core.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}

suspend fun <T> executeWithResult(
    block: suspend () -> T
): Result<T> {
    return try {
        Result.Success(block())
    } catch (e: Throwable) {
        Result.Error(e)
    }
}

fun <T> executeWithResultFlow(
    block: suspend () -> T
): Flow<Result<T>> {
    return flow {
        emit(Result.Loading)
        val result = block()
        emit(Result.Success(result))
    }.catch { e ->
        emit(Result.Error(e))
    }
}

fun <T> executeWithResultStreamingFlow(
    block: () -> Flow<T>
): Flow<Result<T>> {
    return block()
        .map<T, Result<T>> {
            Result.Success(it)
        }
        .onStart {
            emit(Result.Loading)
        }
        .catch {
            emit(Result.Error(it))
        }
}