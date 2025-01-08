package com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.reviews_screen_component

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
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.reviews_screen_component.state.CookReviewUiEvent
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.reviews_screen_component.state.cookReviewEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.reviews_screen_component.state.ReviewErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.reviews_screen_component.state.ReviewState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel  @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val userSession: UserSession,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _reviewState = mutableStateOf(ReviewState())
    val reviewState: State<ReviewState> = _reviewState


    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()




    fun onUiEvent(reviewUiEvent: CookReviewUiEvent) {
        when (reviewUiEvent) {
            //Mobile changed
            is CookReviewUiEvent.CookReviewChanged -> {
                _reviewState.value = _reviewState.value.copy(
                    review = reviewUiEvent.inputValue,
                    errorState = _reviewState.value.errorState.copy(
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
        val review = _reviewState.value.review.trim()

        // Review empty
        if (review.isEmpty()) {
            _reviewState.value = _reviewState.value.copy(
                errorState = ReviewErrorState(
                    reviewErrorState = cookReviewEmptyErrorState
                )
            )
            return false
        }
        // No errors
        else {
            // Set default error state
            _reviewState.value = _reviewState.value.copy(errorState = ReviewErrorState())
            return true
        }
    }

    private fun makeProfileRequestForReview(profileId: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TAG", "makeProfileRequestForReview-> profileId: $profileId")
        //_reviewState.value = _reviewState.value.copy(isLoading = true)
        profileUseCase.invoke(profileId).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _reviewState.value = _reviewState.value.copy(isLoading = false, profileResponse = response, isSuccessful = true)
                        }
                        Log.d("TAG", "makeProfileRequestForReview->: ${Gson().toJson(_reviewState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "makeProfileRequestForReview-> Error: ${Gson().toJson(_reviewState.value)}")
                    _reviewState.value = _reviewState.value.copy(errorMessage = result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "makeProfileRequestForReview->: Loading")
                    _reviewState.value = _reviewState.value.copy(isLoading = true)
                }
            }
        }
    }

    fun setProfileData(profileResponse: ProfileResponse) {
        _reviewState.value = _reviewState.value.copy(isLoading = false, profileResponse = profileResponse, isSuccessful = true)
    }
}