package com.example.cook_ford.data.remote

data class TimeSlotsResponse(
    val afternoon: List<Afternoon>? = emptyList(),
    val evening: List<Evening>? = emptyList(),
    val morning: List<Morning>? = emptyList()
)

data class Morning(
    val id: Int,
    val time: String
)

data class Evening(
    val id: Int,
    val time: String
)

data class Afternoon(
    val id: Int,
    val time: String
)