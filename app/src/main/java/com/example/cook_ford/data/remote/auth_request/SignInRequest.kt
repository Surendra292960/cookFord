package com.example.cook_ford.data.remote.auth_request

import com.example.cook_ford.utils.AppConstants

data class SignInRequest(
    var email: String = AppConstants.EMPTY_STRING,
    var password: String = AppConstants.EMPTY_STRING
)