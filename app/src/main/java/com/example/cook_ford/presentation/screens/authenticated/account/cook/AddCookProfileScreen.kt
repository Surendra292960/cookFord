package com.example.cook_ford.presentation.screens.authenticated.account.cook
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.MultiChoiceCardInFlowRow
import com.example.cook_ford.presentation.component.widgets.SegmentedControl
import com.example.cook_ford.presentation.component.widgets.dialog.CustomDialog
import com.example.cook_ford.presentation.component.widgets.dialog.ResetWarning
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated.account.cook.state.AddCookProfileState
import com.example.cook_ford.presentation.screens.authenticated.account.cook.state.AddCookProfileUiEvent
import com.example.cook_ford.utils.FontName
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


data class Option(val option: String="", val selected: Boolean=false)
@Composable
fun AddCookProfileScreen(
    uploadProfileImage: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit) {
    val addCookProfileViewModel: AddCookProfileViewModel = hiltViewModel()
    val addCookProfileState by remember { addCookProfileViewModel.addCookProfileState }
    val showDialogState: Boolean by addCookProfileViewModel.showDialog.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val changeProfileState = remember { mutableStateOf("") }
    val viewState: MainViewState by addCookProfileViewModel.viewState.collectAsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    if (addCookProfileState.isCookAddedSuccessful) {

        ShowCustomDialog("message", addCookProfileViewModel, showDialogState)

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
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            // verticalArrangement = Arrangement.Center
        ) {
            // Profile Image
            ProfileImage(uploadProfileImage = {}, changeProfileState)

            val options = listOf("Option 1", "Option 2", "Option 3", "Option 4")

            MultiChoiceCardInFlowRow(
                options = options,
                onSelectionChanged = { newSelection ->
                    // Handle the new selection here
                }
            )

            // EditProfile Inputs Composable
            CookProfileForm(
                addCookProfileState,
                addCookProfileViewModel,
                viewState,
                changeProfileImageState = {
                    changeProfileState.value = it
                    Log.d("TAG", "EditProfileScreen: ${changeProfileState.value}")
                })

            // Show Snackbar
            ShowSnackbar(addCookProfileViewModel, lifecycle, snackBarHostState)
        }
    }
}


@Composable
fun ProfileImage(uploadProfileImage:()->Unit, changeProfileState: MutableState<String>) {
    Log.d("TAG", "ProfileImage: ${changeProfileState.value}")

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(160.dp)) {

        Card(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .align(Alignment.Center),
            shape = CircleShape,
            elevation = 2.dp,
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            if (changeProfileState.value == "Female") {
                Image(
                    painter = painterResource(id = R.drawable.female_chef),
                    contentDescription = "Profile Photo",
                    modifier = Modifier,
                    contentScale = ContentScale.Crop,
                )
            }else{
                Image(
                    painter = painterResource(id = R.drawable.male_chef),
                    contentDescription = "Profile Photo",
                    modifier = Modifier,
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}


@Composable
fun CookProfileForm(
    addCookProfileState: AddCookProfileState,
    addCookProfileViewModel: AddCookProfileViewModel,
    viewState: MainViewState,
    changeProfileImageState: (String) -> Unit) {

    // EditProfile Inputs Composable
    AddCookProfileForm(
        addCookProfileState = addCookProfileState,
        viewState = viewState,

        onUserNameChange = { inputString ->
            addCookProfileViewModel.onUiEvent(
                addCookProfileUiEvent = AddCookProfileUiEvent.UserNameChanged(
                    inputString
                )
            )
        },
        onPhoneChange = { inputString ->
            addCookProfileViewModel.onUiEvent(
                addCookProfileUiEvent = AddCookProfileUiEvent.PhoneChanged(
                    inputString
                )
            )
        },
        onAlternatePhoneChange = { inputString ->
            addCookProfileViewModel.onUiEvent(
                addCookProfileUiEvent = AddCookProfileUiEvent.AlternatePhoneChanged(
                    inputString
                )
            )
        },
        onGenderChange = { inputString ->
            changeProfileImageState(inputString)
            addCookProfileViewModel.onUiEvent(
                addCookProfileUiEvent = AddCookProfileUiEvent.GenderChange(
                    inputString
                )
            )
        },
        onSubmit = {
            addCookProfileViewModel.onUiEvent(addCookProfileUiEvent = AddCookProfileUiEvent.Submit)
        },
        //onSignOutClick = onNavigateToSignOut
    )

}


@Composable
fun ShowSnackbar(addCookProfileViewModel: AddCookProfileViewModel, lifecycle: Lifecycle, snackBarHostState: SnackbarHostState) {
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                addCookProfileViewModel.onProcessSuccess.collectLatest { message: String ->
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
    addCookProfileViewModel: AddCookProfileViewModel,
    showDialogState: Boolean) {

    val isDismiss = remember { mutableStateOf(true) }

    CustomDialog(
        showDialog = showDialogState,
        isAnimate = isDismiss.value,
        onDismissRequest =  addCookProfileViewModel::onDialogDismiss) {
        ResetWarning(color= Color.Green, title = title,  onDismissRequest = { })
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    AddCookProfileScreen(onNavigateToAuthenticatedRoute = {}, uploadProfileImage = {}, onNavigateBack = {})
}
