package com.example.cook_ford.data.remote.auth_request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("username")
    var username: String = "",
    @SerializedName("email")
    var email: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("role")
    var role: String = "admin",
    @SerializedName("gender")
    var gender: String = "",
    @SerializedName("phone")
    var phone: String = "") {
    fun isNotEmpty(): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }
}
