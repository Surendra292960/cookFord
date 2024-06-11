package com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.TitleText
import com.example.cook_ford.presentation.component.widgets.dialog.CustomDialog
import com.example.cook_ford.presentation.component.widgets.dialog.ResetWarning
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.state.SignInUiEvent
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.Cook_fordTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = hiltViewModel(),
    onNavigateToRegistration: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit) {
    val signInState by remember { signInViewModel.signInState }
    val showDialogState: Boolean by signInViewModel.showDialog.collectAsState()
    val signInResponse by signInViewModel.signInResponse.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val viewState: MainViewState by signInViewModel.viewState.collectAsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    if (signInState.isSignInSuccessful) {

        ShowCustomDialog(signInResponse.message, signInViewModel, showDialogState)

        Log.d("TAG", "SignInScreen: $showDialogState")
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
            Column(modifier = Modifier.fillMaxSize().navigationBarsPadding().imePadding().padding(padding).verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                // Main card Content for Login
                ElevatedCard(modifier = Modifier.fillMaxWidth().padding(AppTheme.dimens.paddingLarge)) {

                    Spacer(modifier = Modifier.height(10.dp))

                    // Image
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Change the logo
                        Image(
                            painter = painterResource(id = R.drawable.cook_ford_rounded_logo),
                            contentDescription = "Logo",
                            //modifier = Modifier.scale(3f))
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                        )
                    }

                    Column(modifier = Modifier.padding(horizontal = AppTheme.dimens.paddingLarge).padding(bottom = AppTheme.dimens.paddingExtraLarge)) {

                        // Heading Login
                        TitleText(
                            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                            text = stringResource(id = R.string.sign_in_heading_text)
                        )

                        // Login Inputs Composable
                        SignInForm(
                            signInState = signInState,
                            viewState = viewState,
                            onEmailChange = { inputString ->
                                signInViewModel.onUiEvent(
                                    signInUiEvent = SignInUiEvent.EmailChanged(
                                        inputString
                                    )
                                )
                            },
                            onPasswordChange = { inputString ->
                                signInViewModel.onUiEvent(
                                    signInUiEvent = SignInUiEvent.PasswordChanged(
                                        inputString
                                    )
                                )
                            },
                            onSubmit = {
                                signInViewModel.onUiEvent(signInUiEvent = SignInUiEvent.Submit)
                            },
                            onForgotPasswordClick = onNavigateToForgotPassword
                        )
                    }

                    // Register Section
                    Row(
                        modifier = Modifier.padding(AppTheme.dimens.paddingNormal),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        // Don't have an account?
                        Text(text = stringResource(id = R.string.do_not_have_account))

                        //Register
                        Text(
                            modifier = Modifier
                                .padding(start = AppTheme.dimens.paddingExtraSmall)
                                .clickable {
                                    onNavigateToRegistration.invoke()
                                },
                            text = stringResource(id = R.string.register),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                ShowSnackbar(signInViewModel, lifecycle, snackBarHostState)
            }
        })
    }
}

@Composable
fun ShowSnackbar(signInViewModel: SignInViewModel, lifecycle: Lifecycle, snackBarHostState: SnackbarHostState) {
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                signInViewModel.onProcessSuccess.collectLatest { message: String ->
                    Log.d("TAG", "SignInForm: Event success")
                    snackBarHostState.showSnackbar(message)
                }
            }
        }
    }
}

@Composable
fun ShowCustomDialog(
    title: String,
    signInViewModel: SignInViewModel,
    showDialogState: Boolean) {

    val isDismiss = remember { mutableStateOf(true) }

    CustomDialog(
        showDialog = showDialogState,
        isAnimate = isDismiss.value,
        onDismissRequest =  signInViewModel::onDialogDismiss) {
        ResetWarning(color= Color.Green, title = title,  onDismissRequest = { })
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewScreen() {
    Cook_fordTheme {
        SignInScreen(
            onNavigateToForgotPassword = {},
            onNavigateToRegistration = {},
            onNavigateToAuthenticatedRoute = {}
        )
    }
}

