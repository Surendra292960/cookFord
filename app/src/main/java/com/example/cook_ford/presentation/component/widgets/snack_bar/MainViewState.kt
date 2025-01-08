package com.example.cook_ford.presentation.component.widgets.snack_bar
import com.example.cook_ford.presentation.component.widgets.snack_bar.state_events.StateEvent
import com.example.cook_ford.presentation.component.widgets.snack_bar.state_events.StateEventWithContent
import com.example.cook_ford.presentation.component.widgets.snack_bar.state_events.consumed

data class MainViewState(
    val isLoading: Boolean = false,
    val processSuccessEvent: StateEvent = consumed,
    val processSuccessWithTimestampEvent: StateEventWithContent<String> = consumed(),
)