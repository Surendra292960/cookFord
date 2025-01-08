package com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.details_screen_component.state.note_satate

sealed class NoteUiEvent {
    data class NoteChanged(val inputValue: String) : NoteUiEvent()
    data object Submit : NoteUiEvent()
}