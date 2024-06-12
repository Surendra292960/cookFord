package com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.report_screen_component.state

sealed class ReportUiEvent {
    data class ReportChanged(val inputValue: String) : ReportUiEvent()
    data class IssueChanged(val inputValue: String) : ReportUiEvent()
    data object Submit : ReportUiEvent()
}