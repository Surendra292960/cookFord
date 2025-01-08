package com.example.cook_ford.data.remote.profile_response

import android.os.Parcelable
import com.example.cook_ford.data.JsonNavType
import com.example.cook_ford.utils.AppConstants
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileResponse(
    val __v: Int? = AppConstants.ZERO,
    val _id: String? = AppConstants.EMPTY_STRING,
    val createdAt: String? = AppConstants.EMPTY_STRING,
    val email: String? = AppConstants.EMPTY_STRING,
    val gender: String? = AppConstants.EMPTY_STRING,
    val location: Location? = null,
    val password: String? = AppConstants.EMPTY_STRING,
    val phone: String? = AppConstants.EMPTY_STRING,
    val profile: Profile? = null,
    val updatedAt: String? = AppConstants.EMPTY_STRING,
    val userType: String? = AppConstants.EMPTY_STRING,
    val username: String? = AppConstants.EMPTY_STRING,
    var status: String? = AppConstants.EMPTY_STRING,
) : Parcelable

@Parcelize
data class Profile(
    val __v: Int,
    val _id: String,
    val about: String,
    val age: Int,
    val bio: String,
    val city: String,
    val country: String,
    val coverPhotoUrls: List<String>,
    val coverphoto: String?=null,
    val createdAt: String,
    val cuisine: List<String>,
    val cuisine_type: String,
    val experience: Int,
    val feedback_rating: List<FeedbackRating>,
    val feedback_review: List<FeedbackReview>,
    val firstname: String,
    val food_Type: List<String>,
    val from: String,
    val fulltimeprice: Int,
    val headline: String,
    val language: List<String>,
    val lastname: String,
    val no_of_visit: String,
    val parttimeprice: Int,
    val postalcode: Int,
    val profilePhotoUrls: List<String>,
    val profilephoto: String?=null,
    val religion: String,
    val specialities: List<String>,
    val state: String,
    val streetaddress: String,
    val timeSlots: List<TimeSlot>,
    val topCuisineUrls: List<String>,
    val total_rating: Int,
    val updatedAt: String,
    val user: String,
    val afternoon: List<Afternoon>,
    val evening: List<Evening>,
    val morning: List<Morning>,
) : Parcelable

@Parcelize
data class Location(
    val coordinates: List<Double>,
    val type: String = AppConstants.EMPTY_STRING
) : Parcelable

@Parcelize
data class FeedbackRating(
    val _id: String? = AppConstants.EMPTY_STRING,
    val cleanliness: Int? = AppConstants.ZERO,
    val date: String? = AppConstants.EMPTY_STRING,
    val feedback_reviews: List<FeedbackReview>,
    val food_quality: Int? = AppConstants.ZERO,
    val hygiene: Int? = AppConstants.ZERO,
    val punctuality: Int? = AppConstants.ZERO,
    val service: Int? = AppConstants.ZERO
) : Parcelable

@Parcelize
data class FeedbackReview(
    val _id: String? = AppConstants.EMPTY_STRING,
    val body: String? = AppConstants.EMPTY_STRING,
    val date: String? = AppConstants.EMPTY_STRING
) : Parcelable


@Parcelize
data class TimeSlot(
    //val _id: String,
    val endTime: String? = AppConstants.EMPTY_STRING,
    val startTime: String? = AppConstants.EMPTY_STRING
) : Parcelable

@Parcelize
data class Morning(
    val _id: String,
    val id: Int,
    val time: String
): Parcelable

@Parcelize
data class Evening(
    val _id: String,
    val id: Int,
    val time: String
): Parcelable

@Parcelize
data class Afternoon(
    val _id: String,
    val id: Int,
    val time: String
): Parcelable

class ProfileArgType : JsonNavType<ProfileResponse>() {
    override fun fromJsonParse(value: String): ProfileResponse =
        Gson().fromJson(value, ProfileResponse::class.java)

    override fun ProfileResponse.getJsonParse(): String = Gson().toJson(this)
}

data class Posts(val url: String, val name: String)
data class TimeSlots(var slots: String? = AppConstants.EMPTY_STRING, var selected: Boolean = false)


