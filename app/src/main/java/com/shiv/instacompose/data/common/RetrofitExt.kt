package com.shiv.instacompose.data.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeoutOrNull
import retrofit2.Response
import java.net.HttpURLConnection

const val JOB_TIMEOUT = 60000L
fun <T> execute(call: suspend () -> Response<T>): Flow<ApiResult<T>> {
    return flow<ApiResult<T>> {
        emit(ApiResult.Loading)
        try {
            withTimeoutOrNull(JOB_TIMEOUT) {
                val response = call()
                return@withTimeoutOrNull if (response.isSuccessful) {
                    emit(ApiResult.Success(response.body()!!))
                } else {
                    emit(ApiResult.Error(response.message(), response.code()))
                }
            }
        } catch (ex: Exception) {
            emit(
                ApiResult.Error(
                    ex.message ?: ex.toString(),
                    HttpURLConnection.HTTP_UNAVAILABLE
                )
            )
        }
    }
}