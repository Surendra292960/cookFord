package com.example.cook_ford.data.remote.auth_response

import com.example.cook_ford.utils.AppConstants

data class SignInResponse(
    val accessToken : String = AppConstants.EMPTY_STRING,
    val user_id : String = AppConstants.EMPTY_STRING,
    val message : String = AppConstants.EMPTY_STRING,
    val userType : String = AppConstants.EMPTY_STRING
)
