package com.example.cook_ford.presentation.common.customeComposableViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.cook_ford.presentation.theme.AppTheme

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
}

@Composable
fun MediumTitleText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        letterSpacing = 1.sp,
        style = MaterialTheme.typography.bodyLarge,
        // color = MaterialTheme.colorScheme.onPrimary,
        textAlign = textAlign,
        fontWeight = FontWeight.Bold
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
            .fillMaxWidth()) {
        Column(modifier = modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {
            SubTitleText(
                modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                text = title,
                textAlign = TextAlign.Center
            )
            SubTitleText(
                modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                text = text,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ChildText(text: String, modifier: Modifier) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        modifier = modifier,
    )
}