package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state

import com.example.cook_ford.data.remote.CuisineResponse
import com.example.cook_ford.data.remote.LanguagesResponse
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING


/**
 * EditProfile State holding ui input values
 */
data class EditCookProfileState(
    val workArea: String = EMPTY_STRING,
    val username: String = EMPTY_STRING,
    val dob: String = EMPTY_STRING,
    val address: String = EMPTY_STRING,
    val religion: String = EMPTY_STRING,
    val experience: String = EMPTY_STRING,
    val salary: String = EMPTY_STRING,
    val numberOfVisit: String = EMPTY_STRING,
    val foodType: String = EMPTY_STRING,
    val phone: String = EMPTY_STRING,
    val alternatePhone: String = EMPTY_STRING,
    val gender: String = EMPTY_STRING,
    val profileImage: String = EMPTY_STRING,
    val city: String = EMPTY_STRING,
    val jobType: MutableSet<String>?= mutableSetOf(),
    val cuisine: MutableSet<String>?= mutableSetOf(),
    val languages: MutableSet<String>?= mutableSetOf(),
    val errorState: EditCookProfileErrorState = EditCookProfileErrorState(),
    var isLanguageLoadedSuccessful: Boolean = false,
    var isCuisineLoadedSuccessful: Boolean = false,
    var isProfileLoadedSuccessful: Boolean = false,
    val languagesResponse: LanguagesResponse? = null,
    val cuisineResponse: CuisineResponse? = null,
    val profileResponse: ProfileResponse? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    var isCookEditSuccessful: Boolean = false
)

/**
 * Error state in Profile holding respective
 * text field validation errors
 */
data class EditCookProfileErrorState(
    val workAreaErrorState: ErrorState = ErrorState(),
    val usernameErrorState: ErrorState = ErrorState(),
    val dobErrorState: ErrorState = ErrorState(),
    val addressErrorState: ErrorState = ErrorState(),
    val religionErrorState: ErrorState = ErrorState(),
    val experienceErrorState: ErrorState = ErrorState(),
    val salaryErrorState: ErrorState = ErrorState(),
    val numberOfVisitErrorState: ErrorState = ErrorState(),
    val foodTypeErrorState: ErrorState = ErrorState(),
    val phoneErrorState: ErrorState = ErrorState(),
    val alternatePhoneErrorState: ErrorState = ErrorState(),
    val genderErrorState: ErrorState = ErrorState(),
    val cityErrorState: ErrorState = ErrorState(),
    val jobTypeErrorState: ErrorState = ErrorState(),
    val profileImageErrorState: ErrorState = ErrorState(),
    val cuisinesErrorState: ErrorState = ErrorState(),
    val languagesErrorState: ErrorState = ErrorState(),
)