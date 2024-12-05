package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.account_screen_component
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.OutlinedSubmitButton
import com.example.cook_ford.presentation.component.widgets.StarRatingBar
import com.example.cook_ford.presentation.component.widgets.Textarea
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.account_screen_component.state.CookReviewState
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.utils.AppConstants

@Composable
fun CookReviewForm(
    cookReviewState: CookReviewState,
    //viewState: MainViewState,
    modifier: Modifier,
    onReviewChange: (String) -> Unit,
    onRatingChange: (Float) -> Unit,
    onSubmit: () -> Unit) {

    Column(modifier = modifier) {

        Spacer(modifier = Modifier.height(10.dp))

        StarRatingBar(
            maxStars = 5,
            rating = cookReviewState.rating,
            onRatingChanged = onRatingChange
        )

        Spacer(modifier = Modifier.height(20.dp))

        Textarea(
            value = cookReviewState.review,
            onChange = onReviewChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                label = AppConstants.LABEL_REVIEW,
                placeholder = AppConstants.REVIEW_PLACEHOLDER
            ),
            isError = cookReviewState.errorState.reviewErrorState.hasError,
            errorText = stringResource(id = cookReviewState.errorState.reviewErrorState.errorMessageStringResource),
            maxChar = 800
            /*submit = { TODO() }*/
        )

        // Submit Button
        OutlinedSubmitButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.save_button_text),
            textColor = Color.Gray,
            isLoading = false,
            onClick = onSubmit
        )
    }
}
