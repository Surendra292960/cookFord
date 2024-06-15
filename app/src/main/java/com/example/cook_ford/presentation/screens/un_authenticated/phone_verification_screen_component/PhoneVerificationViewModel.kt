package com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.PhoneVerificationErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.PhoneVerificationState
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.PhoneVerificationUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.invalidPhoneEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.phoneEmptyErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PhoneVerificationViewModel @Inject constructor(): ViewModel() {
    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
    private var showOneTapUI = true

    var phoneVerificationState = mutableStateOf(PhoneVerificationState())

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    fun onUiEvent(phoneVerificationUiEvent: PhoneVerificationUiEvent) {
       when(phoneVerificationUiEvent){
           // Update phone
           is PhoneVerificationUiEvent.PhoneChanged -> {
               Log.d("TAG", "onUiEvent: ${phoneVerificationUiEvent.inputValue}")
               phoneVerificationState.value = phoneVerificationState.value.copy(
                   phone = phoneVerificationUiEvent.inputValue,
                   errorState = phoneVerificationState.value.errorState.copy(
                       phoneErrorState = if (phoneVerificationUiEvent.inputValue.trim().isNotEmpty())
                           ErrorState()
                       else
                           phoneEmptyErrorState
                   )
               )
           }

           // Submit Phone'
           is PhoneVerificationUiEvent.Submit -> {
               val inputsValidated = validateInputs()
               Log.d("TAG", "onUiEvent: $inputsValidated")
               if (inputsValidated) {
                   phoneVerificationState.value = phoneVerificationState.value.copy(isOTPSent = true)
                   // TODO Trigger login in authentication flow
               }
           }
       }
    }

    private fun validateInputs(): Boolean {
        val phone = phoneVerificationState.value.phone.trim()

        //Phone Number Empty
        if (phone.isEmpty()) {
            phoneVerificationState.value = phoneVerificationState.value.copy(
                errorState = PhoneVerificationErrorState(
                    phoneErrorState = phoneEmptyErrorState
                )
            )
            return false
        }

        // Phone Number Matcher
        if (phone.isNotEmpty() && phone.length != 10) {
            phoneVerificationState.value = phoneVerificationState.value.copy(
                errorState = PhoneVerificationErrorState(
                    phoneErrorState = invalidPhoneEmptyErrorState
                )
            )
            return false
        }


        // No errors
        else {
            // Set default error state
            phoneVerificationState.value = phoneVerificationState.value.copy(errorState = PhoneVerificationErrorState())
            return true
        }

    }
    /* fun onUiEvent(phoneVerificationUiEvent: PhoneVerificationUiEvent.SendOtp) {
         phoneVerificationState.value = phoneVerificationState.value.copy(
             isOtpSent = true
         )
     }*/
}