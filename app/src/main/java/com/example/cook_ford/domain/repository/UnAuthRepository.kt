package com.example.cook_ford.domain.repository

import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.auth_request.SignInRequest
import com.example.cook_ford.data.remote.auth_request.SignUpRequest
import com.example.cook_ford.data.remote.auth_response.SignInResponse
import com.example.cook_ford.data.remote.auth_response.SignUpResponse
import kotlinx.coroutines.flow.Flow

interface UnAuthRepository {
    suspend fun signIn(signInRequest: SignInRequest): Flow<NetworkResult<SignInResponse>>
    suspend fun signUp(signUpRequest: SignUpRequest):Flow<NetworkResult<SignUpResponse>>
}