package com.example.cook_ford.presentation.screens.sign_in.state

sealed class DialogEvent {
    data class DismissDialog(val inputValue: Boolean) : DialogEvent()
}