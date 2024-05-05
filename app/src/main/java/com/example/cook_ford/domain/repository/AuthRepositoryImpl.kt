package com.example.cook_ford.domain.repository

import com.example.cook_ford.data.remote.request.SignInRequest
import com.example.cook_ford.data.remote.request.SignUpRequest
import com.example.cook_ford.data.remote.response.SignInResponse
import com.example.cook_ford.data.remote.response.SignUpResponse
import com.example.cook_ford.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AuthRepositoryImpl {
    suspend fun signIn(signInRequest: SignInRequest): Flow<NetworkResult<SignInResponse>>
    suspend fun signUp(signUpRequest: SignUpRequest):Flow<NetworkResult<SignUpResponse>>
}