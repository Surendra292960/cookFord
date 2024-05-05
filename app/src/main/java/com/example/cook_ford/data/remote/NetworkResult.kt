package com.example.cook_ford.data.remote

sealed class NetworkResult<T>(val data: T? = null, val status:Boolean?=false, val message: String? = null) {
    class Success<T>(data: T, status:Boolean) : NetworkResult<T>(data,status)
    class Error<T>(message: String, data: T? = null,status:Boolean) : NetworkResult<T>(data, status, message)
    class Loading<T> : NetworkResult<T>()
}