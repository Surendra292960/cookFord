package com.example.cook_ford.presentation.screens.authenticated.accounts.update_profile_screen_component
import android.util.Log
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.widgets.Progressbar
import com.example.cook_ford.presentation.component.widgets.dialog.CustomDialog
import com.example.cook_ford.presentation.component.widgets.dialog.ResetWarning
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated.accounts.update_profile_screen_component.state.EditProfileState
import com.example.cook_ford.presentation.screens.authenticated.accounts.update_profile_screen_component.state.EditProfileUiEvent
import com.example.cook_ford.utils.AppConstants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse?=null,
    onNavigateToSignOut: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit) {
    val editProfileViewModel: EditProfileViewModel = hiltViewModel()
    val editProfileState by remember { editProfileViewModel.editProfileState }
    val showDialogState: Boolean by editProfileViewModel.showDialog.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val changeProfileState = remember { mutableStateOf(AppConstants.EMPTY_STRING) }
    val viewState: MainViewState by editProfileViewModel.viewState.collectAsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    Progressbar(editProfileState.isLoading)
    Log.d("TAG", "ProfileListScreen isLoading: ${editProfileState.isLoading}")
    LaunchedEffect(key1 = Unit) {
        editProfileViewModel.setProfileData(profileResponse)

    }

    if (editProfileState.isEditSuccessful) {

        ShowCustomDialog("message", editProfileViewModel, showDialogState)

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
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .height(300.dp)
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {

            ProfileForm(
                changeProfileState = changeProfileState,
                editProfileState = editProfileState,
                editProfileViewModel = editProfileViewModel,
                viewState = viewState,
                onNavigateToSignOut = onNavigateToSignOut,
                changeProfileImageState={
                changeProfileState.value = it
                Log.d("TAG", "EditProfileScreen: ${changeProfileState.value}")
            })

            ShowSnackbar(editProfileViewModel, lifecycle, snackBarHostState)
        }
    }
}



@Composable
fun ProfileForm(
    changeProfileState: MutableState<String>,
    editProfileState: EditProfileState,
    editProfileViewModel: EditProfileViewModel,
    viewState: MainViewState,
    onNavigateToSignOut: () -> Unit,
    changeProfileImageState: (String) -> Unit) {

    // EditProfile Inputs Composable
    EditProfileForm(
        changeProfileState = changeProfileState,
        editProfileState = editProfileState,
        viewState = viewState,


        onProfileImageChange = { inputString ->
            Log.d("TAG", "EditProfileForm: $inputString")
            editProfileViewModel.onUiEvent(
                editProfileUiEvent = EditProfileUiEvent.ProfileImageChanged(
                    inputString
                )
            )
        },

        onUserNameChange = { inputString ->
            Log.d("TAG", "EditProfileForm: $inputString")
            editProfileViewModel.onUiEvent(
                editProfileUiEvent = EditProfileUiEvent.UserNameChanged(
                    inputString
                )
            )
        },
        onEmailChange = { inputString ->
            editProfileViewModel.onUiEvent(
                editProfileUiEvent = EditProfileUiEvent.EmailChanged(
                    inputString
                )
            )
        },
        onPhoneChange = { inputString ->
            editProfileViewModel.onUiEvent(
                editProfileUiEvent = EditProfileUiEvent.PhoneChanged(
                    inputString
                )
            )
        },
        onGenderChange = { inputString ->
            changeProfileImageState(inputString)
            editProfileViewModel.onUiEvent(
                editProfileUiEvent = EditProfileUiEvent.GenderChange(
                    inputString
                )
            )
        },
        onSubmit = {
            editProfileViewModel.onUiEvent(editProfileUiEvent = EditProfileUiEvent.Submit)
        },
        onSignOutClick = onNavigateToSignOut
    )
}

@Composable
fun ShowSnackbar(editProfileViewModel: EditProfileViewModel, lifecycle: Lifecycle, snackBarHostState: SnackbarHostState) {
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
    editProfileViewModel: EditProfileViewModel,
    showDialogState: Boolean) {

    val isDismiss = remember { mutableStateOf(true) }

    CustomDialog(
        showDialog = showDialogState,
        isAnimate = isDismiss.value,
        onDismissRequest =  editProfileViewModel::onDialogDismiss) {
        ResetWarning(color= Color.Green, title = title,  onDismissRequest = { })
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    EditProfileScreen(onNavigateToSignOut = {}, onNavigateToAuthenticatedRoute = {}, onNavigateBack = {})
}
