package com.example.cook_ford.data.remote.auth_request

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("email")
    var email: String = "",
    @SerializedName("password")
    var password: String = ""
)