package com.example.cook_ford.presentation.screens.dashboard_component.home_screen_component

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.cook_ford.data.local.SessionConstant
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.domain.use_cases.authenticated_use_case.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val userSession: UserSession,
    private val stateHandle: SavedStateHandle
):ViewModel() {


    fun getUserType():String?{
        return userSession.getString(SessionConstant.USER_TYPE)
    }
}