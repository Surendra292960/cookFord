package com.example.cook_ford.data

object ApiConstants {
    const val BASE_URL = "https://cookfordbackendserver-production.up.railway.app/"

    const val SIGN_IN_END_POINT = "api/users/login"
    const val SIGN_UP_END_POINT = "api/users/signup"

    const val PROFILE_END_POINT = "api/users/getAllprofile"
    const val PROFILE_DETAILS_END_POINT = "api/users/profile/{id}"
    const val USERS_NEARBY_END_POINT = "api/users/profileNearUser"
}