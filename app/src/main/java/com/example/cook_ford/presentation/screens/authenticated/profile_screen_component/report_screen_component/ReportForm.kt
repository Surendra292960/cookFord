package com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.report_screen_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.MediumTitleText
import com.example.cook_ford.presentation.component.widgets.OutlinedSubmitButton
import com.example.cook_ford.presentation.component.widgets.Textarea
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.report_screen_component.state.ReportState
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

            SingleSelectionComponent(
                modifier = Modifier,
                issueList = reportViewModel.issueList,
                onIssueChange = onIssueChange
            )

            Spacer(modifier = Modifier.height(10.dp))

            Textarea(
                value = reportState.report,
                onChange = onReportChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                keyboardOptions = KeyboardOption(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text,label = AppConstants.LABEL_REPORT, placeholder = AppConstants.REPORT_PLACEHOLDER),
                isError = reportState.errorState.reportErrorState.hasError,
                errorText = stringResource(id = reportState.errorState.reportErrorState.errorMessageStringResource),
                maxChar = 800
                /*submit = { TODO() }*/)

            Spacer(modifier = Modifier.height(10.dp))


        OutlinedSubmitButton(
            modifier = Modifier.padding(all = 10.dp),
            textColor = Color.Gray,
            text = stringResource(id = R.string.submit_button_text),
            isLoading = viewState.isLoading,
            onClick = onSubmit
        )

            Spacer(modifier = Modifier.height(10.dp))
        }
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SingleSelectionComponent(modifier: Modifier, issueList: List<String>, onIssueChange: (String) -> Unit) {
    val selectedIndex = remember { mutableIntStateOf(-1) }

    FlowRow(modifier = modifier.padding(2.dp)) {
        issueList.forEachIndexed { index, issue ->
            FilterChip(
                shape = RoundedCornerShape(1.dp),
                modifier = Modifier.padding(horizontal = 1.dp).background(Color.White),
                onClick = {
                    selectedIndex.intValue = index
                    onIssueChange(issueList[selectedIndex.intValue])
                },
                label = {
                    MediumTitleText(
                        modifier = Modifier,
                        text = issue,
                        fontWeight = FontWeight.W500,
                        textAlign = TextAlign.Center,
                        textColor = Color.DarkGray
                    )
                },
                colors = if (selectedIndex.intValue == index) {
                    /**
                     * selected colors
                     */
                    FilterChipDefaults.filterChipColors(selectedContainerColor = Color.LightGray)
                    }else {
                    /**
                     * not selected colors
                     */
                    FilterChipDefaults.filterChipColors(disabledContainerColor = Color.Transparent)
                },
                selected = selectedIndex.intValue == index,/*
                leadingIcon = if (selectedIndex.intValue == index) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },*/
            )
        }
    }

}

