package com.example.cook_ford.presentation.component.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.cook_ford.presentation.theme.AppTheme

/*
@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.secondary
    )
}*/

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    fontWeight:FontWeight,
    textAlign: TextAlign = TextAlign.Start) {
    Text(
        modifier = modifier,
        text = text,
        letterSpacing = 0.5.sp,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontSize = 15.sp,
        color = textColor
    )
}

@Composable
fun MediumTitleText(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    fontWeight:FontWeight,
    textAlign: TextAlign = TextAlign.Start) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = textAlign,
        fontWeight = fontWeight,
        color = textColor
    )
}


@Composable
fun SubTitleText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start) {
    Text(
        modifier = modifier,
        text = text,
        letterSpacing = 1.sp,
        style = MaterialTheme.typography.bodyMedium,
        //color = MaterialTheme.colorScheme.onPrimary,
        textAlign = textAlign
    )
}

@Composable
fun ErrorTextInputField(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.error
    )
}


@Composable
fun Child(modifier: Modifier, title: String, text: String) {
    Box(modifier = modifier
            .fillMaxWidth().padding(top = AppTheme.dimens.paddingSmall, bottom = AppTheme.dimens.paddingSmall)) {
        Column(modifier = modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {

            MediumTitleText(
                modifier = Modifier,
                text = title,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                textColor = Color.DarkGray
            )

            MediumTitleText(
                modifier = Modifier,
                text = text,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                textColor = Color.Gray
            )
        }
    }
}
