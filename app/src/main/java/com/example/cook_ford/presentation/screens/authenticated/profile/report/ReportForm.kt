package com.example.cook_ford.presentation.screens.authenticated.profile.report

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.SubmitButton
import com.example.cook_ford.presentation.component.widgets.Textarea
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated.profile.report.state.ReportState
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.utils.AppConstants

@Composable
fun ReportForm(
    reportState: ReportState,
    viewState: MainViewState,
    modifier: Modifier,
    onReportChange: (String) -> Unit,
    onSubmit: () -> Unit){

        Column(modifier = modifier) {

            Spacer(modifier = Modifier.height(10.dp))

            Textarea(
                value = reportState.report,
                onChange = onReportChange,
                modifier = Modifier.fillMaxWidth().height(200.dp),
                keyboardOptions = KeyboardOption(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text,label = AppConstants.REPORT, placeholder = AppConstants.REPORT_PLACEHOLDER),
                isError = reportState.errorState.reportErrorState.hasError,
                errorText = stringResource(id = reportState.errorState.reportErrorState.errorMessageStringResource),
                maxChar = 800
                /*submit = { TODO() }*/)

            Spacer(modifier = Modifier.height(10.dp))

            // SignIn Submit Button
            SubmitButton(
                modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                text = stringResource(id = R.string.submit_button_text),
                isLoading = viewState.isLoading,
                onClick = onSubmit
            )
        }
}

