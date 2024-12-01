package com.example.cook_ford.data.remote.cook_preferences

class CookPreferences : ArrayList<CookPreferencesItem>()

data class CookPreferencesItem(
    val afternoon: List<Afternoon>,
    val cuisine: List<Cuisine>,
    val evening: List<Evening>,
    val language: List<Language>,
    val morning: List<Morning>
)

data class Morning(
    val id: Int,
    val time: String
)

data class Afternoon(
    val id: Int,
    val time: String
)
data class Evening(
    val id: Int,
    val time: String
)
data class Cuisine(
    val cuisinetype: String,
    val id: Int
)
data class Language(
    val id: Int,
    val langtype: String
)