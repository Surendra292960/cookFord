package com.example.cook_ford.presentation.screens.authenticated.accounts.account_screen_component

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.SessionConstant
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.domain.use_cases.ProfileUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated.accounts.account_screen_component.state.AccountState
import com.example.cook_ford.presentation.screens.authenticated.accounts.account_screen_component.state.ReviewErrorState
import com.example.cook_ford.presentation.screens.authenticated.accounts.account_screen_component.state.ReviewState
import com.example.cook_ford.presentation.screens.authenticated.accounts.account_screen_component.state.ReviewUiEvent
import com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.details_screen_component.state.note_satate.ratingEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.details_screen_component.state.note_satate.reviewEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.state.ErrorState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
private val userSession: UserSession,
private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _accountState = mutableStateOf(AccountState())
    val accountState: State<AccountState> = _accountState

    val reviewState = mutableStateOf(ReviewState())

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    init {
        userSession.getString(SessionConstant.USER_ID)?.let { getProfileRequest(it) }
    }

    fun onReViewUiEvent(reviewUiEvent: ReviewUiEvent) {
        when (reviewUiEvent) {
            //Rating changed
            is ReviewUiEvent.RatingChanged -> {
                reviewState.value = reviewState.value.copy(
                    rating = reviewUiEvent.inputValue,
                    errorState = reviewState.value.errorState.copy(
                        reviewErrorState = if (reviewUiEvent.inputValue != 0.0f)
                            ErrorState()
                        else
                            ratingEmptyErrorState
                    )
                )
            }
            //Review changed
            is ReviewUiEvent.ReViewChanged -> {
                reviewState.value = reviewState.value.copy(
                    review = reviewUiEvent.inputValue,
                    errorState = reviewState.value.errorState.copy(
                        reviewErrorState = if (reviewUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            reviewEmptyErrorState
                    )
                )
            }

            ReviewUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    reviewState.value = reviewState.value.copy(isSuccessful = true)
                    //reviewState.value = ReviewState()
                    Log.d("TAG", "onReViewUiEvent: ${Gson().toJson(reviewState.value)}")
                    // TODO Trigger login in authentication flow
                }
            }
        }
    }


    private fun validateInputs(): Boolean {
        val review = reviewState.value.review.trim()

        // Review empty
        if (review.isEmpty()) {
            reviewState.value = reviewState.value.copy(
                errorState = ReviewErrorState(
                    reviewErrorState = reviewEmptyErrorState
                )
            )
            return false
        }
        // No errors
        else {
            // Set default error state
            reviewState.value = reviewState.value.copy(errorState = ReviewErrorState())
            return true
        }
    }

    private fun makeProfileRequest(profileId: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TAG", "makeProfileRequest profileId: $profileId")
        // _accountState.value = _accountState.value.copy(isLoading = true)
        profileUseCase.invoke(profileId).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _accountState.value = _accountState.value.copy(isLoading = false, profileResponse = response, isSuccessful = true)
                        }
                        Log.d("TAG", "makeProfileRequest-> getProfileResponse: ${Gson().toJson(_accountState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "makeProfileRequest-> Error: ${Gson().toJson(_accountState.value)}")
                    _accountState.value = _accountState.value.copy(errorMessage = result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "makeProfileRequest->: Loading")
                    _accountState.value = _accountState.value.copy(isLoading = true)
                }
            }
        }
    }

    private fun getProfileRequest(profileId: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TAG", "makeProfileRequest profileId: $profileId")
        // _accountState.value = _accountState.value.copy(isLoading = true)
        profileUseCase.invoke(profileId).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _accountState.value = _accountState.value.copy(isLoading = false, profileResponse = response, isSuccessful = true)
                        }
                        Log.d("TAG", "makeProfileRequest-> getProfileResponse: ${Gson().toJson(_accountState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "makeProfileRequest-> Error: ${Gson().toJson(_accountState.value)}")
                    _accountState.value = _accountState.value.copy(errorMessage = result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "makeProfileRequest->: Loading")
                    _accountState.value = _accountState.value.copy(isLoading = true)
                }
            }
        }
    }
}