package com.example.cook_ford.data.remote

sealed class NetworkResult<T>(val data: T? = null, val status:Boolean?=false, val message: String? = null) {
    class Success<T>(data: T, status:Boolean) : NetworkResult<T>(data = data, status = status)
    class Error<T>(message: String, status:Boolean) : NetworkResult<T>( message = message, status = status)
    class Loading<T> : NetworkResult<T>()
}