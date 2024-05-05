package com.example.cook_ford.data.remote.response
import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("_id")
    val _id : String,
    @SerializedName("email")
    val email : String,
    @SerializedName("message")
    val message:String
)
