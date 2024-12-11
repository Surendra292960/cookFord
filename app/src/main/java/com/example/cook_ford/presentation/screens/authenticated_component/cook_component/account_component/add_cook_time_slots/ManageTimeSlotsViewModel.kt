package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_time_slots

import androidx.lifecycle.ViewModel
import com.example.cook_ford.data.local.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManageTimeSlotsViewModel @Inject constructor(private val userSession: UserSession) : ViewModel() {

}