package com.example.cook_ford.presentation.screens.authenticated_screen_component.accounts_screen_component.cook_preferences_component

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CookPreferencesViewModel @Inject constructor() : ViewModel() {
    val selectedItem = mutableSetOf<String>()
}