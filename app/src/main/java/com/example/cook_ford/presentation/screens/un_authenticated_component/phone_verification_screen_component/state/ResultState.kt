package com.example.cook_ford.presentation.screens.un_authenticated_component.phone_verification_screen_component.state

sealed class ResultState<out T>(val otp: String?=null, val data: T?=null, val msg: String? = null, val isLoading: Boolean? = false) {

    class Success<out T>(otp:String, data:T) : ResultState<T>(otp = otp, data = data)
    class Failure(msg:Throwable) : ResultState<Nothing>(msg = msg.message)
    class Loading(isLoading:Boolean) : ResultState<Nothing>(isLoading = isLoading)

}