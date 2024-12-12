package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.cook_available_jobs_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.cook_available_jobs_list.state.CookJobState
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import javax.inject.Inject

@HiltViewModel
class CookJobViewModel @Inject constructor():ViewModel(){
    private val _state = mutableStateOf(CookJobState())
    val state: State<CookJobState> = _state


}