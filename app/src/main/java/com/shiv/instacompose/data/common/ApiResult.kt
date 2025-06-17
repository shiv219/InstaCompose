package com.shiv.instacompose.data.common

import android.telephony.SmsMessage.MessageClass

sealed interface ApiResult<out T> {
    data class Success<out T>(val data: T): ApiResult<T>
    data class Error(val message: String, val statusCode: Int): ApiResult<Nothing>
    data object Loading : ApiResult<Nothing>
}