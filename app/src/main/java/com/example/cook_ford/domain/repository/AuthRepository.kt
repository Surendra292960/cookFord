package com.example.cook_ford.domain.repository

import com.example.cook_ford.data.remote.CuisineResponse
import com.example.cook_ford.data.remote.LanguagesResponse
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.TimeSlotsResponse
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun getProfiles():Flow<NetworkResult<List<ProfileResponse>>>
    suspend fun getProfileById(profileId: String):Flow<NetworkResult<ProfileResponse>>
    suspend fun getTimeSlots():Flow<NetworkResult<TimeSlotsResponse>>
    suspend fun getLanguages():Flow<NetworkResult<LanguagesResponse>>
    suspend fun getCuisines():Flow<NetworkResult<CuisineResponse>>


}