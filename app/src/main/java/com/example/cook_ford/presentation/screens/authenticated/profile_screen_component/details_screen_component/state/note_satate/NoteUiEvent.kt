package com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.details_screen_component.state.note_satate

sealed class NoteUiEvent {
    data class NoteChanged(val inputValue: String) : NoteUiEvent()
    data object Submit : NoteUiEvent()
}