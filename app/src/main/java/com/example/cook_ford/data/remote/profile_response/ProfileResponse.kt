package com.example.cook_ford.data.remote.profile_response

data class ProfileResponse(
    val __v: Int,
    val _id: String,
    val about: String,
    val age: Int,
    val bio: String,
    val coverPhotoUrls: List<String>,
    val createdAt: String,
    val cuisine: String,
    val experience: Int,
    val feedback_rating: FeedbackRating,
    val headline: String,
    val language: String,
    val location: Location,
    val price: Int,
    val profilePhotoUrls: List<String>,
    val rating: Any,
    val specialities: List<Any>,
    val topCuisineUrls: List<String>,
    val total_rating: Int,
    val updatedAt: String,
    val user: User
)

data class FeedbackRating(
    val cleanliness: Int,
    val feedback_reviews: Int,
    val food_quality: Int,
    val hygiene: Int,
    val punctuality: Int,
    val service: Int
)

data class Location(
    val coordinates: List<Double>,
    val type: String
)

data class User(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val gender: String,
    val location: Location,
    val password: String,
    val phone: String,
    val profile: String,
    val updatedAt: String,
    val userType: String,
    val username: String
)