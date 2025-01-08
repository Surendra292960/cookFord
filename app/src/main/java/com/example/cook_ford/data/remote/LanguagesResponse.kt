package com.example.cook_ford.data.remote

data class LanguagesResponse(
    val language: List<Language>,
)
data class Language(
    val id: Int,
    val langtype: String
)