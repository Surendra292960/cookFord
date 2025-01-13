package com.example.cook_ford.presentation.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExposureZero
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.welcome.ModelData
import com.example.cook_ford.presentation.component.widgets.ButtonIcons
import com.example.cook_ford.presentation.component.widgets.LargeSubText
import com.example.cook_ford.presentation.component.widgets.MediumTitleText
import com.example.cook_ford.presentation.component.widgets.OutlinedSmallSubmitButton
import com.example.cook_ford.presentation.component.widgets.OutlinedSubmitButton
import com.example.cook_ford.presentation.component.widgets.SmallTitleText
import com.example.cook_ford.presentation.component.widgets.TitleText
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.details_screen_component.AddNote
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.DeepGreen
import com.example.cook_ford.presentation.theme.OrangeYellow1
import com.example.cook_ford.utils.AppConstants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    status: String,
    modelData: MutableList<ModelData> = mutableListOf(),
    onContinue: () -> Unit,
    onDismiss: () -> Unit,
    onNavigateToCallCreditScreen: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    Log.d("TAG", "WelcomeBottomSheet : ")
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = null
    ) {
        if (status.equals(AppConstants.ADD_NOTE_SHEET, ignoreCase = true)) {
            AddNote()
        } else {
            WelcomeBottomSheet(
                modelData = modelData,
                onContinue = { onContinue() },
                onNavigateToCallCreditScreen = { onNavigateToCallCreditScreen() },
                status = status
            )
        }
    }
}

@Composable
fun WelcomeBottomSheet(
    modelData: MutableList<ModelData>,
    onContinue: () -> Unit,
    onNavigateToCallCreditScreen: () -> Unit,
    status: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 20.dp)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp, Alignment.CenterVertically)
    ) {


        if (status.equals(AppConstants.WELCOME_SHEET, ignoreCase = true)) {
            TitleText(
                modifier = Modifier,
                text = AppConstants.WELCOME,
                textAlign = TextAlign.Start,
                textColor = Color.DarkGray,
                fontWeight = FontWeight.ExtraBold
            )
        }
        else if (status.equals(AppConstants.BUY_CALL_CREDIT_SHEET, ignoreCase = true)) {
            LargeSubText(
                modifier = Modifier,
                text = AppConstants.CALL_BEFORE_CREDIT,
                textAlign = TextAlign.Start,
                textColor = Color.DarkGray,
                fontWeight = FontWeight.W900
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(AppTheme.dimens.paddingSmall),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            items(modelData.size) { index ->

                if (index == AppConstants.ZERO && status.equals(AppConstants.WELCOME_SHEET, ignoreCase = true)) {
                    SmallTitleText(
                        modifier = Modifier.align(Alignment.Start),
                        text = modelData[index].title,
                        textAlign = TextAlign.Start,
                        textColor = Color.DarkGray,
                        fontWeight = FontWeight.W500
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .background(
                                    modelData[index].backgroundColor,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .wrapContentSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            if (status.equals(AppConstants.WELCOME_SHEET, ignoreCase = true)) {
                                Box(modifier = Modifier
                                    .size(25.dp)
                                    .background(
                                        modelData[index].leadingIconTintColor,
                                        shape = RectangleShape
                                    ),
                                    contentAlignment = Alignment.Center
                                ) {

                                    MediumTitleText(
                                        modifier = Modifier,
                                        text = index.toString(),
                                        textAlign = TextAlign.Start,
                                        textColor = Color.White,
                                        fontWeight = FontWeight.W900
                                    )
                                }
                            } else {
                                Icon(
                                    painter = painterResource(id = modelData[index].leadingIcon),
                                    tint = modelData[index].leadingIconTintColor,
                                    contentDescription = ""
                                )
                            }

                            Column(
                                modifier = Modifier.padding(start = 15.dp),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center
                            ) {

                                MediumTitleText(
                                    modifier = Modifier.align(Alignment.Start),
                                    text = modelData[index].title,
                                    textAlign = TextAlign.Start,
                                    textColor = Color.DarkGray,
                                    fontWeight = FontWeight.W700
                                )
                            }
                        }
                    }
                }
            }
        }
        if (status.equals(AppConstants.WELCOME_SHEET, ignoreCase = true)) {
            OutlinedSubmitButton(
                modifier = Modifier.padding(all = 20.dp),
                textColor = Color.Gray,
                text = stringResource(id = R.string.submit_button_continue),
                isLoading = false,
                onClick = { onContinue.invoke() }
            )
        } else if (status.equals(AppConstants.BUY_CALL_CREDIT_SHEET, ignoreCase = true)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp, start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedSmallSubmitButton(
                    modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge).weight(1f),
                    text = AppConstants.CALL_CREDIT,
                    textColor = Color.White,
                    isLoading = false,
                    backgroundColor = Color.LightGray,
                    icon = ButtonIcons(leadingIcon = Icons.Default.ExposureZero, tintColor = DeepGreen, leadingIconSize = 30.dp),
                    onClick = { /*onSubmit*/ }
                )

                Spacer(modifier = Modifier.width(20.dp))

                OutlinedSmallSubmitButton(
                    modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge).weight(1f),
                    text = AppConstants.BUY_CALL_CREDIT,
                    textColor = Color.White,
                    isLoading = false,
                    backgroundColor = OrangeYellow1,
                    onClick = onNavigateToCallCreditScreen
                    /*navController.currentBackStackEntry?.savedStateHandle?.apply {
                        set(
                            "profileResponse",
                            Gson().toJson(accountState.profileResponse)
                        )
                    }
                    onNavigateToCallCreditScreen.invoke()*/
                )
            }
        }
    }
}