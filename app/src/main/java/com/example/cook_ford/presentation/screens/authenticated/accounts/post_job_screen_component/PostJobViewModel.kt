package com.example.cook_ford.presentation.screens.authenticated.accounts.post_job_screen_component
import androidx.lifecycle.ViewModel
import com.example.cook_ford.data.local.SessionConstant
import com.example.cook_ford.data.local.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostJobViewModel @Inject constructor(private val userSession: UserSession) : ViewModel() {

    val phoneNumber = userSession.getString(SessionConstant.PHONE_NUMBER)

}