package com.example.cook_ford.presentation.screens.authenticated.profile.details

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
import com.example.cook_ford.presentation.screens.authenticated.profile.details.state.note_satate.NoteState
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.utils.AppConstants

@Composable
fun NoteForm(
    noteState: NoteState,
    //viewState: MainViewState,
    modifier: Modifier,
    onNoteChange: (String) -> Unit,
    onSubmit: () -> Unit) {

    Column(modifier = modifier) {

        Spacer(modifier = Modifier.height(10.dp))

        Textarea(
            value = noteState.note,
            onChange = onNoteChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                label = AppConstants.NOTE,
                placeholder = AppConstants.NOTE_PLACEHOLDER
            ),
            isError = noteState.errorState.noteErrorState.hasError,
            errorText = stringResource(id = noteState.errorState.noteErrorState.errorMessageStringResource),
            maxChar = 800
            /*submit = { TODO() }*/
        )

        Spacer(modifier = Modifier.height(30.dp))

        // SignIn Submit Button
        SubmitButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.save_button_text),
            isLoading = false,
            onClick = onSubmit
        )
    }
}

