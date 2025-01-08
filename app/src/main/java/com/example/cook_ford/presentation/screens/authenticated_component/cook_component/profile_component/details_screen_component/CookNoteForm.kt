package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component

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
import com.example.cook_ford.presentation.component.widgets.Textarea
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.state.note_satate.CookNoteState
import com.example.cook_ford.utils.AppConstants

@Composable
fun CookNoteForm(
    cookNoteState: CookNoteState,
    viewState: Boolean,
    modifier: Modifier,
    onNoteChange: (String) -> Unit,
    onSubmit: () -> Unit) {

    Column(modifier = modifier) {

        Spacer(modifier = Modifier.height(10.dp))

        Textarea(
            value = cookNoteState.note,
            onChange = onNoteChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                label = AppConstants.LABEL_NOTE,
                placeholder = AppConstants.NOTE_PLACEHOLDER
            ),
            isError = cookNoteState.errorState.noteErrorState.hasError,
            errorText = stringResource(id = cookNoteState.errorState.noteErrorState.errorMessageStringResource),
            maxChar = 800
            /*submit = { TODO() }*/
        )

        Spacer(modifier = Modifier.height(30.dp))

        // SignIn Submit Button

        OutlinedSubmitButton(
            modifier = Modifier.padding(all = 10.dp),
            textColor = Color.Gray,
            text = stringResource(id = R.string.submit_button_text),
            isLoading = false,
            onClick = onSubmit
        )
    }
}

