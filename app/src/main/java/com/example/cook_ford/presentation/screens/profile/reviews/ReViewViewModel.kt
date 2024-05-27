package com.example.cook_ford.presentation.screens.profile.reviews

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.domain.use_cases.ProfileUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.profile.details.state.note_satate.noteEmptyErrorState
import com.example.cook_ford.presentation.screens.profile.reviews.state.ReviewErrorState
import com.example.cook_ford.presentation.screens.profile.reviews.state.ReviewState
import com.example.cook_ford.presentation.screens.profile.reviews.state.ReviewUiEvent
import com.example.cook_ford.presentation.screens.profile.reviews.state.reviewEmptyErrorState
import com.example.cook_ford.presentation.screens.sign_in.state.ErrorState
import com.example.cook_ford.utils.AppConstants
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReViewViewModel  @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val userSession: UserSession,
    private val stateHandle: SavedStateHandle
) : ViewModel() {
   
    private val _reviewState = MutableStateFlow(ReviewState())
    val reviewState = _reviewState.asStateFlow()

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    init {
        getProfileId()?.let {
            Log.d("TAG", " stateHandle  : $it")
            getProfileId()?.let { makeProfileRequestForReview(profileId = it) }
        }
    }

    fun getProfileId() = stateHandle.get<String>(AppConstants.PROFILE_ID)

    fun onUiEvent(reviewUiEvent: ReviewUiEvent) {
        when (reviewUiEvent) {
            //Mobile changed
            is ReviewUiEvent.ReviewChanged -> {
                _reviewState.value = _reviewState.value.copy(
                    review = reviewUiEvent.inputValue,
                    errorState = _reviewState.value.errorState.copy(
                        reviewErrorState = if (reviewUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            reviewEmptyErrorState
                    )
                )
            }

            ReviewUiEvent.Submit -> {
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
                    reviewErrorState = reviewEmptyErrorState
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
                            _reviewState.value = _reviewState.value.copy(isLoading = false, profile = response, isSuccessful = true)
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
}