package com.example.cook_ford.data.remote.profile_response
import com.example.cook_ford.utils.AppConstants

data class ProfileResponse(
    val __v: Int?=AppConstants.ZERO,
    val _id: String?=AppConstants.EMPTY_STRING,
    val createdAt: String?=AppConstants.EMPTY_STRING,
    val email: String?=AppConstants.EMPTY_STRING,
    val gender: String?=AppConstants.EMPTY_STRING,
    val location: Location,
    val name: String?=AppConstants.EMPTY_STRING,
    val password: String?=AppConstants.EMPTY_STRING,
    val phone: String?=AppConstants.EMPTY_STRING,
    val profile: Profile,
    val tc: Boolean?=false,
    val updatedAt: String?=AppConstants.EMPTY_STRING,
    val userType: String?=AppConstants.EMPTY_STRING,
    val username: String?=AppConstants.EMPTY_STRING,
    val usertype: String?=AppConstants.EMPTY_STRING
)

data class Profile(
    val __v: Int?=AppConstants.ZERO,
    val _id: String?=AppConstants.EMPTY_STRING,
    val about: String?=AppConstants.EMPTY_STRING,
    val age: Int?=AppConstants.ZERO,
    val bio: String?=AppConstants.EMPTY_STRING,
    val coverPhotoUrls: List<Any>?= emptyList(),
    val createdAt: String?=AppConstants.EMPTY_STRING,
    val cuisine: String?=AppConstants.EMPTY_STRING,
    val experience: Int?=AppConstants.ZERO,
    val feedback_rating: FeedbackRating,
    val fulltimeprice: Int?=AppConstants.ZERO,
    val headline: String?=AppConstants.EMPTY_STRING,
    val language: String?=AppConstants.EMPTY_STRING,
    val parttimeprice: Int?=AppConstants.ZERO,
    val profilePhotoUrls: List<Any>?= emptyList(),
    val specialities: List<Any>?=emptyList(),
    val timeSlots: List<Any>?=emptyList(),
    val topCuisineUrls: List<Any>?=emptyList(),
    val total_rating: Int?=AppConstants.ZERO,
    val updatedAt: String?=AppConstants.EMPTY_STRING
)

data class Location(
    val coordinates: List<Double>,
    val type: String
)

data class FeedbackRating(
    val cleanliness: Int?=AppConstants.ZERO,
    val feedback_reviews: List<Any>?= emptyList(),
    val food_quality: Int?=AppConstants.ZERO,
    val hygiene: Int?=AppConstants.ZERO,
    val punctuality: Int?=AppConstants.ZERO,
    val service: Int?=AppConstants.ZERO
)