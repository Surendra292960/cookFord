package com.example.cook_ford.presentation.screens.authenticated.profile.details.state.note_satate
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

data class NoteState(
    val note: String = EMPTY_STRING,
    val errorState: NoteErrorState = NoteErrorState(),
    val isLoading: Boolean = false,
    val profile: ProfileResponse? = null,
    val errorMessage: String = "",
    var isSuccessful: Boolean = false
)

/**
 * Error state in Note holding respective
 * text field validation errors
 */
data class NoteErrorState(
    val noteErrorState: ErrorState = ErrorState()
)