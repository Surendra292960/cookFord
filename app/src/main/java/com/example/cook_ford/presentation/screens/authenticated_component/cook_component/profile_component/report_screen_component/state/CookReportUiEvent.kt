package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.report_screen_component.state


sealed class CookReportUiEvent {
    data class CookReportChanged(val inputValue: String) : CookReportUiEvent()
    data class CookIssueChanged(val inputValue: String) : CookReportUiEvent()
    data object Submit : CookReportUiEvent()
}