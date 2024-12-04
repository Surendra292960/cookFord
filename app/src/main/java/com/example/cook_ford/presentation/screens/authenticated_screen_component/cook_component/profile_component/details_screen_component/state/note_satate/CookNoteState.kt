package com.example.cook_ford.presentation.screens.authenticated_screen_component.cook_component.profile_component.details_screen_component.state.note_satate
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.authenticated_screen_component.user_component.profile_component.details_screen_component.state.note_satate.NoteErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

data class CookNoteState(
    val note: String = EMPTY_STRING,
    val errorState: CookNoteErrorState = CookNoteErrorState(),
    val isLoading: Boolean = false,
    val profile: ProfileResponse? = null,
    val errorMessage: String = "",
    var isSuccessful: Boolean = false
)

/**
 * Error state in Note holding respective
 * text field validation errors
 */
data class CookNoteErrorState(
    val noteErrorState: ErrorState = ErrorState()
)