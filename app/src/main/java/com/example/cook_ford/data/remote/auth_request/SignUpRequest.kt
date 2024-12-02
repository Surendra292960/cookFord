package com.example.cook_ford.data.remote.auth_request
import com.example.cook_ford.utils.AppConstants

data class SignUpRequest(
    var username: String = AppConstants.EMPTY_STRING,
    var email: String = AppConstants.EMPTY_STRING,
    var password: String = AppConstants.EMPTY_STRING,
    var userType: String = AppConstants.EMPTY_STRING,
    var gender: String = AppConstants.EMPTY_STRING,
    var phone: String = AppConstants.EMPTY_STRING,
    var latitude: String = AppConstants.EMPTY_STRING,
    var longitude: String = AppConstants.EMPTY_STRING
) {
    fun isNotEmpty(): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }
}
