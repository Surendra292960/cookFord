package com.example.cook_ford.presentation.screens.profile.profile_review

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cook_ford.data.remote.auth_request.SignInRequest
import com.example.cook_ford.presentation.common.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.profile.profile_review.state.ReviewErrorState
import com.example.cook_ford.presentation.screens.profile.profile_review.state.ReviewState
import com.example.cook_ford.presentation.screens.profile.profile_review.state.ReviewUiEvent
import com.example.cook_ford.presentation.screens.profile.profile_review.state.reviewEmptyErrorState
import com.example.cook_ford.presentation.screens.sign_in.state.ErrorState
import com.example.cook_ford.presentation.screens.sign_in.state.SignInErrorState
import com.example.cook_ford.presentation.screens.sign_in.state.SignInUiEvent
import com.example.cook_ford.presentation.screens.sign_in.state.phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.sign_up.state.emailEmptyErrorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReViewViewModel:ViewModel() {
    var reviewState = mutableStateOf(ReviewState())
        private set

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()


    fun onUiEvent(reviewUiEvent: ReviewUiEvent) {
        when (reviewUiEvent) {
            //Mobile changed
            is ReviewUiEvent.ReviewChanged -> {
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
                Log.d("TAG", "onUiEvent: $inputsValidated")
                if (inputsValidated) {
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
}