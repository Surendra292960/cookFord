package com.example.cook_ford.domain.repository

import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.auth_request.SignInRequest
import com.example.cook_ford.data.remote.auth_request.SignUpRequest
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.data.remote.auth_response.SignInResponse
import com.example.cook_ford.data.remote.auth_response.SignUpResponse
import com.example.cook_ford.data.remote.profile_request.ProfileRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepositoryImpl {
    suspend fun signIn(signInRequest: SignInRequest): Flow<NetworkResult<SignInResponse>>
    suspend fun signUp(signUpRequest: SignUpRequest):Flow<NetworkResult<SignUpResponse>>
    suspend fun getProfile():Flow<NetworkResult<List<ProfileResponse>>>
    suspend fun getProfileById(profileId: String):Flow<NetworkResult<ProfileResponse>>
}