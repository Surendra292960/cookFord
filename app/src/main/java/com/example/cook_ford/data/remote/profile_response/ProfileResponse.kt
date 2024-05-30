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
    val __v: Int?=AppConstants.ZERO,
    val _id: String=AppConstants.EMPTY_STRING,
    val about: String=AppConstants.EMPTY_STRING,
    val age: Int=AppConstants.ZERO,
    val bio: String=AppConstants.EMPTY_STRING,
    val coverPhotoUrls: List<String>?= emptyList(),
    val createdAt: String=AppConstants.EMPTY_STRING,
    val cuisine: String=AppConstants.EMPTY_STRING,
    val experience: Int?=AppConstants.ZERO,
    val feedback_rating: List<FeedbackRating>,
    val food_Type: String=AppConstants.EMPTY_STRING,
    val from: String?=AppConstants.EMPTY_STRING,
    val fulltimeprice: Int=AppConstants.ZERO,
    val headline: String=AppConstants.EMPTY_STRING,
    val language: String=AppConstants.EMPTY_STRING,
    val no_of_visit: String?=AppConstants.EMPTY_STRING,
    val parttimeprice: Int?=AppConstants.ZERO,
    val profilePhotoUrls: List<String>?= emptyList(),
    val specialities: List<String>?= emptyList(),
    val timeSlots: List<TimeSlot>?= emptyList(),
    val topCuisineUrls: Array<String>?= emptyArray<String>(),
    val total_rating: Int?=AppConstants.ZERO,
    val updatedAt: String?=AppConstants.EMPTY_STRING,
    val user: String?=AppConstants.EMPTY_STRING
)

data class Location(
    val coordinates: List<Double>,
    val type: String=AppConstants.EMPTY_STRING
)

data class FeedbackRating(
    val _id: String?=AppConstants.EMPTY_STRING,
    val cleanliness: Int?=AppConstants.ZERO,
    val date: String?=AppConstants.EMPTY_STRING,
    val feedback_reviews: List<FeedbackReview>,
    val food_quality: Int?=AppConstants.ZERO,
    val hygiene: Int?=AppConstants.ZERO,
    val punctuality: Int?=AppConstants.ZERO,
    val service: Int?=AppConstants.ZERO
)

data class FeedbackReview(
    val _id: String?=AppConstants.EMPTY_STRING,
    val body: String?=AppConstants.EMPTY_STRING,
    val date: String?=AppConstants.EMPTY_STRING
)


data class TimeSlot(
    //val _id: String,
    val endTime: String?=AppConstants.EMPTY_STRING,
    val startTime: String?=AppConstants.EMPTY_STRING
)


data class Posts(val url: String, val name: String)
data class TimeSlots(var slots: String?= AppConstants.EMPTY_STRING, val initialSelection: Boolean = false){
    var selected by mutableStateOf(initialSelection)
}

