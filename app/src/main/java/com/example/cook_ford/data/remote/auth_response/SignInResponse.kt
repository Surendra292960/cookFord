package com.example.cook_ford.data.remote.auth_response

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("accessToken")
    val accessToken : String = "",
    @SerializedName("_id")
    val _id : String = "",
    @SerializedName("message")
    val message : String = ""
)