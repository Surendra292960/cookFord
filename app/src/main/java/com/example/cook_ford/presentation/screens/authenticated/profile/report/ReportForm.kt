package com.example.cook_ford.presentation.screens.authenticated.profile.report

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
    onIssueChange: (String) -> Unit,
    onReportChange: (String) -> Unit,
    onSubmit: () -> Unit){
    val reportViewModel: ReportViewModel = hiltViewModel()
        Column(modifier = modifier) {

            Spacer(modifier = Modifier.height(10.dp))

            IssueComponent(issueList = reportViewModel.issueList, onIssueChange = {
                Log.d("TAG", "ReportForm: $it")
            })

            Spacer(modifier = Modifier.height(10.dp))

            Textarea(
                value = reportState.report,
                onChange = onReportChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
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



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IssueComponent(issueList: List<String>, onIssueChange: (String) -> Unit) {
    FlowRow {
        issueList.forEach { issue ->
            issue?.let {
                SelectIssue(
                    issue = issue,
                    onIssueChange = onIssueChange
                )
            }
        }
    }
}

@Composable
fun SelectIssue(issue: String, onIssueChange: (String) -> Unit) {
    var selected by remember { mutableStateOf(false) }
    //Log.d("TAG", "FilterChipExample: ${Gson().toJson(reportViewModel.selectedItem)}")

    FilterChip(
        modifier = Modifier.padding(horizontal = 5.dp),
        onClick = {
            if (selected) {
                selected = false
                onIssueChange.invoke("")
            } else {
                selected = true
                onIssueChange.invoke(issue)
            }
        },
        label = {
            Text(issue)
        },
        selected = selected,

    )
    /*FilterChip(
        modifier = Modifier.padding(horizontal = 5.dp),
        onClick = {
            selected = !selected
            if (!selected) {
                onIssueChange.invoke("")
            } else {
                onIssueChange.invoke(issue)
            }
        },
        label = {
            Text(issue)
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )*/
}
