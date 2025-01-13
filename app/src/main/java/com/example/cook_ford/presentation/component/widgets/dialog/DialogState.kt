package com.example.cook_ford.presentation.component.widgets.dialog

import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

data class DialogState(
    var showDialogState: Boolean = false,
    var dismissDialogState: Boolean = false,
    var message: String = EMPTY_STRING,
)
