package com.example.cook_ford.presentation.screens.authenticated.account.profile
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.dialog.CustomDialog
import com.example.cook_ford.presentation.component.widgets.dialog.ResetWarning
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated.account.profile.state.EditProfileUiEvent
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.utils.FontName
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    onNavigateToSignOut: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit) {
    val editProfileViewModel: EditProfileViewModel = hiltViewModel()
    val editProfileState by remember { editProfileViewModel.editProfileState }
    val showDialogState: Boolean by editProfileViewModel.showDialog.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val viewState: MainViewState by editProfileViewModel.viewState.collectAsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

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

        Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) }, content = { padding ->
            // Full Screen Content
            Column(modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .imePadding()
                .padding(padding)
                .verticalScroll(
                    rememberScrollState()
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
               // verticalArrangement = Arrangement.Center
                ) {

                // Main card Content for Edit Profile
                Spacer(modifier = Modifier.height(10.dp))

                // Image
                ProfileImage(uploadProfileImage = {})

                HorizontalDivider(modifier = Modifier.padding(10.dp), color = Color.DarkGray)

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier.padding(10.dp)) {

                    // EditProfile Inputs Composable
                    EditProfileForm(
                        editProfileState = editProfileState,
                        viewState = viewState,

                        onUserNameChange = { inputString ->
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
                        onSubmit = {
                            editProfileViewModel.onUiEvent(editProfileUiEvent = EditProfileUiEvent.Submit)
                        },
                        onSignOutClick = onNavigateToSignOut
                    )
                }

                ShowSnackbar(editProfileViewModel, lifecycle, snackBarHostState)
            }
        })
    }
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
fun ProfileImage(uploadProfileImage:()->Unit) {
    Box(modifier = Modifier.fillMaxWidth()
            .padding(40.dp)
            .size(100.dp)
            .clip(CircleShape)) {
        Image(
            painterResource(id = R.drawable.ic_chef_round),
            contentDescription = "Artist image",
            modifier = Modifier.align(Alignment.Center)
        )
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
    EditProfileScreen(onNavigateToSignOut = {}, onNavigateToAuthenticatedRoute = {})
}
