package com.example.cook_ford.data.remote.profile_response
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.cook_ford.utils.AppConstants
import java.io.Serializable

data class ProfileResponse(
    val __v: Int?=AppConstants.ZERO,
    val _id: String?=AppConstants.EMPTY_STRING,
    val createdAt: String?=AppConstants.EMPTY_STRING,
    val email: String?=AppConstants.EMPTY_STRING,
    val gender: String?=AppConstants.EMPTY_STRING,
    val location: Location,
    val password: String?=AppConstants.EMPTY_STRING,
    val phone: String?=AppConstants.EMPTY_STRING,
    val profile: Profile,
    val updatedAt: String?=AppConstants.EMPTY_STRING,
    val userType: String?=AppConstants.EMPTY_STRING,
    val username: String?=AppConstants.EMPTY_STRING,
)

data class Profile(
    val __v: Int,
    val _id: String,
    val about: String,
    val age: Int,
    val bio: String,
    val coverPhotoUrls: List<String>,
    val createdAt: String,
    val cuisine: String,
    val experience: Int,
    val feedback_rating: List<FeedbackRating>,
    val food_Type: String,
    val from: String,
    val fulltimeprice: Int,
    val headline: String,
    val language: String,
    val no_of_visit: String,
    val parttimeprice: Int,
    val profilePhotoUrls: List<String>,
    val specialities: List<String>,
    val timeSlots: List<TimeSlot>,
    val topCuisineUrls: List<String>,
    val total_rating: Int,
    val updatedAt: String,
    val user: String
)

data class Location(
    val coordinates: List<Double>,
    val type: String=AppConstants.EMPTY_STRING
)

data class FeedbackRating(
    val _id: String,
    val cleanliness: Int,
    val date: String,
    val feedback_reviews: List<FeedbackReview>,
    val food_quality: Int,
    val hygiene: Int,
    val punctuality: Int,
    val service: Int
)

data class FeedbackReview(
    val _id: String,
    val body: String,
    val date: String
)


data class TimeSlot(
    val _id: String,
    val endTime: String,
    val startTime: String
)


data class Posts(val url: String, val name: String)
data class TimeSlots(val slots: String?= AppConstants.EMPTY_STRING, val initialSelection: Boolean = false){
    var selected by mutableStateOf(initialSelection)
}