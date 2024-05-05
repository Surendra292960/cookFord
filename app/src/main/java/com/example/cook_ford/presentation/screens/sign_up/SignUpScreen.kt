package com.example.cook_ford.presentation.screens.sign_up
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cook_ford.R
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.Cook_fordTheme
import com.example.cook_ford.presentation.common.customeComposableViews.TitleText
import com.example.cook_ford.presentation.common.widgets.CustomDialog
import com.example.cook_ford.presentation.common.widgets.DialogState
import com.example.cook_ford.presentation.screens.sign_up.state.SignUpUiEvent
import kotlinx.coroutines.delay


@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit) {

    val registrationState by remember { signUpViewModel.signUpState }
    val dialogState by remember { mutableStateOf(signUpViewModel.dialogState) }

    if (registrationState.isSignUpSuccessful) {
        DisplayDialog(dialogState)
        LaunchedEffect(key1 = true) {
            delay(2000)
            onNavigateToAuthenticatedRoute.invoke()
        }
    } else {
        // Full Screen Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            // Main card Content for Login
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.paddingLarge)) {

                Spacer(modifier = Modifier.height(10.dp))

                // Image
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()) {
                    // Change the logo
                    Image(painter = painterResource(id = R.drawable.cook_ford_rounded_logo),
                        contentDescription = "Logo",
                        //modifier = Modifier.scale(3f))
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp))
                }

                Column(
                    modifier = Modifier
                        .padding(horizontal = AppTheme.dimens.paddingLarge)
                        .padding(bottom = AppTheme.dimens.paddingExtraLarge)
                ) {

                    // Heading Registration
                    TitleText(
                        modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                        text = stringResource(id = R.string.sign_up_heading_text)
                    )


                    SignUpForm(registrationState = registrationState, onNameChange = { inputString ->
                            signUpViewModel.onUiEvent(
                                signUpUiEvent = SignUpUiEvent.NameChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onUserNameChange = { inputString ->
                            signUpViewModel.onUiEvent(
                                signUpUiEvent = SignUpUiEvent.UserNameChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onPhoneChange = { inputString ->
                            signUpViewModel.onUiEvent(
                                signUpUiEvent = SignUpUiEvent.PhoneChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onPasswordChange = { inputString ->
                            signUpViewModel.onUiEvent(
                                signUpUiEvent = SignUpUiEvent.PasswordChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onConfirmPasswordChange = { inputString ->
                            signUpViewModel.onUiEvent(
                                signUpUiEvent = SignUpUiEvent.ConfirmPasswordChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onSubmit = {
                            signUpViewModel.onUiEvent(signUpUiEvent = SignUpUiEvent.Submit)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun DisplayDialog(showDialog: MutableState<DialogState>){
    if(showDialog.value.showDialogState) {
        CustomDialog(
            value = showDialog.value.message, setShowDialog = { showDialog.value.showDialogState = it }) {
            Log.d("HomePage", "HomePage : ${showDialog.value.message}")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen() {
    Cook_fordTheme {
        SignUpScreen(onNavigateBack = {}, onNavigateToAuthenticatedRoute = {})
    }
}