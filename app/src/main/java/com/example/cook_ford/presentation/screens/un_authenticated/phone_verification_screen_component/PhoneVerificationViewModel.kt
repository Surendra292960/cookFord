package com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component
import android.app.Activity
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.repository.AuthRepositoryImpl
import com.example.cook_ford.domain.repository.FirebaseAuthRepository
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.OTPVerificationUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.PhoneVerificationErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.PhoneVerificationState
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.PhoneVerificationUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.ResultState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.invalidPhoneEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.otpEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.phoneEmptyErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneVerificationViewModel @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepository,
    private val authRepository: AuthRepositoryImpl): ViewModel() {

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
                    phoneVerificationState.value = phoneVerificationState.value.copy(nevigateToOTPScreen = true)
                }
            }
        }
    }
    fun onOTPUiEvent(otpVerificationUiEvent: OTPVerificationUiEvent) {

        when(otpVerificationUiEvent){
            // Update phone
            is OTPVerificationUiEvent.OTPChanged -> {
                phoneVerificationState.value = phoneVerificationState.value.copy(
                    otp = otpVerificationUiEvent.inputValue,
                    errorState = phoneVerificationState.value.errorState.copy(
                        otpErrorState = if (otpVerificationUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            otpEmptyErrorState
                    )
                )
            }

            // Submit OTP'
            is OTPVerificationUiEvent.Submit -> {
                val inputsValidated = validateOTPInputs()
                Log.d("TAG", "onUiEvent OTP: $inputsValidated")
                if (inputsValidated) {
                    Log.d("TAG", "onUiEvent OTP: validated")
                    viewmodelSignInWithCred(otp = phoneVerificationState.value.otp)
                }
            }
        }
    }

    private fun viewmodelSignInWithCred(otp: String) {
        viewModelScope.launch(Dispatchers.Main){
            signInWithCredential(otp).collect{
                when(it){
                    is ResultState.Success->{
                        Log.d("TAG", "viewmodelSignInWithCred Success: $it")
                        phoneVerificationState.value = phoneVerificationState.value.copy(nevigateToSignIn = true)
                    }
                    is ResultState.Failure->{
                        Log.d("TAG", "viewmodelSignInWithCred Failure: $it")
                        phoneVerificationState.value = phoneVerificationState.value.copy(nevigateToSignIn = false)
                    }
                    is ResultState.Loading->{
                        Log.d("TAG", "viewmodelSignInWithCred Loading: $it")
                        phoneVerificationState.value = phoneVerificationState.value.copy(nevigateToSignIn = false)
                    }
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

    private fun validateOTPInputs(): Boolean {
        val otp = phoneVerificationState.value.otp.trim()

        // OTP Empty
        if (otp.isEmpty()) {
            phoneVerificationState.value = phoneVerificationState.value.copy(
                errorState = PhoneVerificationErrorState(
                    otpErrorState = otpEmptyErrorState
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

    fun createUserWithPhone(mobile:String, activity: Activity) = firebaseAuthRepo.createUserWithPhone(mobile, activity)

    private fun signInWithCredential(code:String) = firebaseAuthRepo.signWithCredential(code)

}
