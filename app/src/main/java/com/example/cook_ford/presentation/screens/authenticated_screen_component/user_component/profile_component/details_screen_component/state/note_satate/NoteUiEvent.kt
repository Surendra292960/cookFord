package com.example.cook_ford.presentation.screens.authenticated_screen_component.user_component.profile_component.details_screen_component.state.note_satate

import com.example.cook_ford.presentation.screens.authenticated_screen_component.cook_component.profile_component.details_screen_component.state.note_satate.CookNoteUiEvent

sealed class NoteUiEvent {
    data class NoteChanged(val inputValue: String) : NoteUiEvent()
    data object Submit : NoteUiEvent()
}