package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.reviews_screen_component

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.domain.use_cases.authenticated_use_case.ProfileUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.reviews_screen_component.state.CookReviewErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.reviews_screen_component.state.CookReviewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.reviews_screen_component.state.CookReviewUiEvent
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.reviews_screen_component.state.cookReviewEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CookReviewViewModel  @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val userSession: UserSession,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _cookReviewState = mutableStateOf(CookReviewState())
    val cookReviewState: State<CookReviewState> = _cookReviewState


    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()




    fun onUiEvent(reviewUiEvent: CookReviewUiEvent) {
        when (reviewUiEvent) {
            //Mobile changed
            is CookReviewUiEvent.CookReviewChanged -> {
                _cookReviewState.value = _cookReviewState.value.copy(
                    cookReview = reviewUiEvent.inputValue,
                    errorState = _cookReviewState.value.errorState.copy(
                        reviewErrorState = if (reviewUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cookReviewEmptyErrorState
                    )
                )
            }

            CookReviewUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                Log.d("TAG", "onUiEvent: $inputsValidated")
                if (inputsValidated) {
                    // TODO Trigger login in authentication flow
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val review = _cookReviewState.value.cookReview.trim()

        // Review empty
        if (review.isEmpty()) {
            _cookReviewState.value = _cookReviewState.value.copy(
                errorState = CookReviewErrorState(
                    reviewErrorState = cookReviewEmptyErrorState
                )
            )
            return false
        }
        // No errors
        else {
            // Set default error state
            _cookReviewState.value = _cookReviewState.value.copy(errorState = CookReviewErrorState())
            return true
        }
    }

    private fun makeProfileRequestForReview(profileId: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TAG", "makeProfileRequestForReview-> profileId: $profileId")
        //_cookReviewState.value = _cookReviewState.value.copy(isLoading = true)
        profileUseCase.invoke(profileId).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _cookReviewState.value = _cookReviewState.value.copy(isLoading = false, profileResponse = response, isSuccessful = true)
                        }
                        Log.d("TAG", "makeProfileRequestForReview->: ${Gson().toJson(_cookReviewState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "makeProfileRequestForReview-> Error: ${Gson().toJson(_cookReviewState.value)}")
                    _cookReviewState.value = _cookReviewState.value.copy(errorMessage = result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "makeProfileRequestForReview->: Loading")
                    _cookReviewState.value = _cookReviewState.value.copy(isLoading = true)
                }
            }
        }
    }

    fun setProfileData(profileResponse: ProfileResponse) {
        _cookReviewState.value = _cookReviewState.value.copy(isLoading = false, profileResponse = profileResponse, isSuccessful = true)
    }
}