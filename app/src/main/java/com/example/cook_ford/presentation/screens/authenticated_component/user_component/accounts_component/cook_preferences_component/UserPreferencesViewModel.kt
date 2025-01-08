package com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.cook_preferences_component

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserPreferencesViewModel @Inject constructor() : ViewModel() {
    val selectedItem = mutableSetOf<String>()
}