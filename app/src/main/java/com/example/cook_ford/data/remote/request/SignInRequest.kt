package com.example.cook_ford.data.remote.request

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("email")
    var username: String = "",
    @SerializedName("password")
    var password: String = ""
)