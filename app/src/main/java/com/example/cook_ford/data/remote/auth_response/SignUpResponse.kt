package com.example.cook_ford.data.remote.auth_response
import com.example.cook_ford.utils.AppConstants
import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("_id")
    val _id : String = AppConstants.EMPTY_STRING,
    @SerializedName("email")
    val email : String = AppConstants.EMPTY_STRING,
    @SerializedName("message")
    val message:String = AppConstants.EMPTY_STRING
)
