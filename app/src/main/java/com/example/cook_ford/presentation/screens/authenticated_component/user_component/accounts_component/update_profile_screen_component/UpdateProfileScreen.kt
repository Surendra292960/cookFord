package com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.update_profile_screen_component

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.rememberImeState
import com.example.cook_ford.presentation.component.widgets.Progressbar
import com.example.cook_ford.presentation.component.widgets.dialog.CustomDialog
import com.example.cook_ford.presentation.component.widgets.dialog.ResetWarning
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.update_profile_screen_component.state.UpdateProfileState
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.update_profile_screen_component.state.UpdateProfileUiEvent
import com.example.cook_ford.presentation.theme.DeepGreen
import com.example.cook_ford.utils.AppConstants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun UpdateProfileScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse? = null,
    onNavigateToSignOut: (String) -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {
    val updateProfileViewModel: UpdateProfileViewModel = hiltViewModel()
    val updateCookProfileState by remember { updateProfileViewModel.updateProfileState }
    val showDialogState: Boolean by updateProfileViewModel.showDialog.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val changeProfileState = remember { mutableStateOf(AppConstants.EMPTY_STRING) }
    val viewState: MainViewState by updateProfileViewModel.viewState.collectAsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    Progressbar(updateCookProfileState.isLoading)
    Log.d("TAG", "ProfileListScreen isLoading: ${updateCookProfileState.isLoading}")
    LaunchedEffect(key1 = Unit) {
        updateProfileViewModel.setProfileData(profileResponse)

    }

    if (updateCookProfileState.isEditSuccessful) {
        ShowCustomDialog(
            "message",
            updateProfileViewModel,
            showDialogState
        )

        Log.d("TAG", "EditProfileScreen: $showDialogState")
        /**
         * Navigate to Authenticated navigation route
         * once signIn is successful
         */
        if (!showDialogState) {
            LaunchedEffect(key1 = true) {
                onNavigateToAuthenticatedRoute.invoke()
            }
        }
    } else {

        // Full Screen Content
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
                .height(300.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ProfileForm(
                changeProfileState = changeProfileState,
                updateCookProfileState = updateCookProfileState,
                editProfileViewModel = updateProfileViewModel,
                viewState = viewState,
                onNavigateToSignOut = { userType ->
                    onNavigateToSignOut.invoke(userType)
                },
                changeProfileImageState = {
                    changeProfileState.value = it
                    Log.d("TAG", "EditProfileScreen: ${changeProfileState.value}")
                })

            ShowSnackbar(
                updateProfileViewModel,
                lifecycle,
                snackBarHostState
            )
        }
    }
}


@Composable
fun ProfileForm(
    changeProfileState: MutableState<String>,
    updateCookProfileState: UpdateProfileState,
    editProfileViewModel: UpdateProfileViewModel,
    viewState: MainViewState,
    onNavigateToSignOut: (String) -> Unit,
    changeProfileImageState: (String) -> Unit
) {

    // EditProfile Inputs Composable
    UpdateProfileForm(
        changeProfileState = changeProfileState,
        updateCookProfileState = updateCookProfileState,
        viewState = viewState,

        onProfileImageChange = { inputString ->
            Log.d("TAG", "EditProfileForm: $inputString")
            editProfileViewModel.onUiEvent(
                updateProfileUiEvent = UpdateProfileUiEvent.ProfileImageChanged(
                    inputString
                )
            )
        },

        onUserNameChange = { inputString ->
            Log.d("TAG", "EditProfileForm: $inputString")
            editProfileViewModel.onUiEvent(
                updateProfileUiEvent = UpdateProfileUiEvent.UserNameChanged(
                    inputString
                )
            )
        },
        onEmailChange = { inputString ->
            editProfileViewModel.onUiEvent(
                updateProfileUiEvent = UpdateProfileUiEvent.EmailChanged(
                    inputString
                )
            )
        },
        onPhoneChange = { inputString ->
            editProfileViewModel.onUiEvent(
                updateProfileUiEvent = UpdateProfileUiEvent.PhoneChanged(
                    inputString
                )
            )
        },
        onGenderChange = { inputString ->
            changeProfileImageState(inputString)
            editProfileViewModel.onUiEvent(
                updateProfileUiEvent = UpdateProfileUiEvent.GenderChange(
                    inputString
                )
            )
        },
        onSubmit = {
            editProfileViewModel.onUiEvent(updateProfileUiEvent = UpdateProfileUiEvent.Submit)
        },

        onSignOut = { userType ->
            onNavigateToSignOut.invoke(userType)
        }
    )
}

@Composable
fun ShowSnackbar(
    editProfileViewModel: UpdateProfileViewModel,
    lifecycle: Lifecycle,
    snackBarHostState: SnackbarHostState
) {
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                editProfileViewModel.onProcessSuccess.collectLatest { message: String ->
                    Log.d("TAG", "EditProfile: Event success")
                    snackBarHostState.showSnackbar(message)
                }
            }
        }
    }
}

@Composable
fun ShowCustomDialog(
    title: String,
    editProfileViewModel: UpdateProfileViewModel,
    showDialogState: Boolean
) {

    val isDismiss = remember { mutableStateOf(true) }

    CustomDialog(
        showDialog = showDialogState,
        isAnimate = isDismiss.value,
        onDismissRequest = editProfileViewModel::onDialogDismiss
    ) {
        ResetWarning(color = DeepGreen, title = title, onDismissRequest = { })
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    UpdateProfileScreen(
        onNavigateToSignOut = {},
        onNavigateToAuthenticatedRoute = {},
        onNavigateBack = {})
}
