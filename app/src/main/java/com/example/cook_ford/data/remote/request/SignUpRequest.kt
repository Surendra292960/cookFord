package com.example.cook_ford.data.remote.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("username")
    var name: String = "",
    @SerializedName("email")
    var username: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("role")
    var role: String = "",
    @SerializedName("gender")
    var gender: String = "",
    @SerializedName("phone")
    var phone: String = "") {
    fun isNotEmpty(): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }
}
