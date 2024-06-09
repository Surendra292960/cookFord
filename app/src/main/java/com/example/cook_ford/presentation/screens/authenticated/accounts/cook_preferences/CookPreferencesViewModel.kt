package com.example.cook_ford.presentation.screens.authenticated.accounts.cook_preferences

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CookPreferencesViewModel @Inject constructor() : ViewModel() {
    val selectedItem = mutableSetOf<String>()
}