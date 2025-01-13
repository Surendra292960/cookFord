package com.example.cook_ford.data.remote.welcome

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.cook_ford.R
import com.example.cook_ford.presentation.theme.Green
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

data class ModelData(
    @DrawableRes val leadingIcon: Int = R.drawable.check_circle,
    @DrawableRes val trailingIcon: Int = R.drawable.check_circle,
    @DrawableRes val leadingIconTintColor: Color = Color.Gray,
    @DrawableRes val trailingIconTintColor: Color = Color.Gray,
    val isBorder: Boolean = false,
    val backgroundColor: Color = Color.Transparent,
    val title: String = EMPTY_STRING,
    val subTitle: String = EMPTY_STRING
)

val welcomeBottomSheetData = mutableListOf(
    ModelData(
        title = "A little about the process before you continue."
    ),
    ModelData(
        leadingIconTintColor = Green,
        title = "Set your cook preferences."
    ),
    ModelData(
        leadingIconTintColor = Green,
        title = "You will be shown a list of cooks and chef who matches your selection."
    ),
    ModelData(
        leadingIconTintColor = Green,
        title = "Then contact the cook to discuss the requirements and charges."
    ),
    ModelData(
        leadingIconTintColor = Green,
        title = "Conduct a background verification for the cook prior to hiring(optional but recommended).",
    )
)

val byCallCreditBottomSheetData = mutableListOf(
    ModelData(
        leadingIcon = R.drawable.check_circle,
        leadingIconTintColor = Green,
        title = "For reference, tell the cook you their profile on Cook Ford."
    ),
    ModelData(
        leadingIcon = R.drawable.check_circle,
        leadingIconTintColor = Green,
        title = "Discuss the requirements and charges clearly before hiring."
    ),
    ModelData(
        leadingIcon = R.drawable.check_circle,
        leadingIconTintColor = Green,
        title = "Report issue directly from the cook`s profile."
    ),
    ModelData(
        leadingIcon = R.drawable.ic_call,
        leadingIconTintColor = Color.Gray,
        backgroundColor = Color.LightGray,
        title = "This might not be a good time to call the cook. \n" +
                " We recommend calling between 8am-9pm.",
    ),
    ModelData(
        leadingIcon = R.drawable.add_alert,
        leadingIconTintColor = Color.Gray,
        backgroundColor = Color.LightGray,
        title = "1 call credit will be used for contacting the cook.",
    )
)
