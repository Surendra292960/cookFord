package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_cook_component.add_cook_preferences

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CookPreferencesViewModel @Inject constructor() : ViewModel() {
    val selectedItem = mutableSetOf<String>()
}