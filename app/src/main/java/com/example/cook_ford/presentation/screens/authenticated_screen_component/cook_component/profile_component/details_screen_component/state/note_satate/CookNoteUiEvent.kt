package com.example.cook_ford.presentation.screens.authenticated_screen_component.cook_component.profile_component.details_screen_component.state.note_satate

sealed class CookNoteUiEvent {
    data class NoteChanged(val inputValue: String) : CookNoteUiEvent()
    data object Submit : CookNoteUiEvent()
}