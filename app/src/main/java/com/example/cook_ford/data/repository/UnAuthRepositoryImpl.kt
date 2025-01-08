package com.example.cook_ford.data.repository

import com.example.cook_ford.data.ApiService
import com.example.cook_ford.data.remote.BaseRepo
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.auth_request.SignInRequest
import com.example.cook_ford.data.remote.auth_request.SignUpRequest
import com.example.cook_ford.data.remote.auth_response.SignInResponse
import com.example.cook_ford.data.remote.auth_response.SignUpResponse
import com.example.cook_ford.domain.repository.UnAuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UnAuthRepositoryImpl(
    private val apiService: ApiService,
    //private val preferences: AuthPreferences
) : UnAuthRepository, BaseRepo() {

    override suspend fun signIn(signInRequest: SignInRequest): Flow<NetworkResult<SignInResponse>> {
        return flow {
            emit(safeApiCall { apiService.makeSignInRequest(signInRequest) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun signUp(signUpRequest: SignUpRequest): Flow<NetworkResult<SignUpResponse>> {
        return flow {
            emit(safeApiCall { apiService.makeSignUpRequest(signUpRequest) })
        }.flowOn(Dispatchers.IO)
    }
}