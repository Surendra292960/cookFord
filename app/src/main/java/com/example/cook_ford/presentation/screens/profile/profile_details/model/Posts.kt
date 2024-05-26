package com.example.cook_ford.presentation.screens.profile.profile_details.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Posts(val url: String, val name: String)
data class TimeSlots(val slots: String, val initialSelection: Boolean = false){
	var selected by mutableStateOf(initialSelection)
}