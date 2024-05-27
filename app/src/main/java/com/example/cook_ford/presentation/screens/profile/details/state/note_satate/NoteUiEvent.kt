package com.example.cook_ford.presentation.screens.profile.details.state.note_satate

sealed class NoteUiEvent {
    data class NoteChanged(val inputValue: String) : NoteUiEvent()
    data object Submit : NoteUiEvent()
}