package com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.details_screen_component
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.state.note_satate.CookNoteUiEvent

@Composable
fun AddNote() {
	val profileDetailsViewModel: ProfileDetailsViewModel = hiltViewModel()
	//val viewState by remember { profileDetailsViewModel.viewState }
	val noteState by remember { profileDetailsViewModel.noteState }

	Column(modifier = Modifier
		.fillMaxWidth()
		.padding(all = 20.dp)) {

		Spacer(modifier = Modifier.height(10.dp))

		NoteForm(
			noteState = noteState,
			viewState = false,
			modifier = Modifier
				.fillMaxWidth()
				.padding(start = 10.dp, end = 10.dp),
			onNoteChange = { inputString ->
				profileDetailsViewModel.onNoteUiEvent(
					noteUiEvent = CookNoteUiEvent.NoteChanged(
						inputString
					)
				)
			},
			onSubmit = {
				profileDetailsViewModel.onNoteUiEvent(noteUiEvent = CookNoteUiEvent.Submit)
			})
	}
}