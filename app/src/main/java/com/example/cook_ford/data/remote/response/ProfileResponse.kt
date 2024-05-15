package com.example.cook_ford.data.remote.response

import com.example.cook_ford.utils.AppConstants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class ProfileResponse(
    val __v: Int = AppConstants.ZERO,
    val _id: String? = AppConstants.EMPTY_STRING,
    val about: String? = AppConstants.EMPTY_STRING,
    val bio: String? = AppConstants.EMPTY_STRING,
    val comments: String? = AppConstants.EMPTY_STRING,
    val cook: Cook,
    val coverPhotoUrls: List<Any>,
    val createdAt: String? = AppConstants.EMPTY_STRING,
    val cuisine: String? = AppConstants.EMPTY_STRING,
    val experience: Int = AppConstants.ZERO,
    val headline: String? = AppConstants.EMPTY_STRING,
    val language: String? = AppConstants.EMPTY_STRING,
    //val location:List<Any> ,
    val price: Int = AppConstants.ZERO,
    val profilePhotoUrls: List<Any>,
    val rating: Int = AppConstants.ZERO,
    val time_slotes: String? = AppConstants.EMPTY_STRING,
    val topCuisineUrls: List<Any>,
    val updatedAt: String?
)

data class Location(
    val type: String? = AppConstants.EMPTY_STRING,
)

data class Cook(
    val __v: Int? = AppConstants.ZERO,
    val _id: String? = AppConstants.EMPTY_STRING,
    val createdAt: String? = AppConstants.EMPTY_STRING,
    val email: String? = AppConstants.EMPTY_STRING,
    val gender: String? = AppConstants.EMPTY_STRING,
    val password: String? = AppConstants.EMPTY_STRING,
    val phone: String? = AppConstants.EMPTY_STRING,
    val updatedAt: String? = AppConstants.EMPTY_STRING,
    val username: String? = AppConstants.EMPTY_STRING
)