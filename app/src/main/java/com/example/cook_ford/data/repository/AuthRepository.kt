package com.example.cook_ford.data.repository

import com.example.cook_ford.data.remote.BaseRepo
import com.example.cook_ford.data.ApiService
import com.example.cook_ford.data.remote.request.SignInRequest
import com.example.cook_ford.data.remote.request.SignUpRequest
import com.example.cook_ford.data.remote.response.SignInResponse
import com.example.cook_ford.data.remote.response.SignUpResponse
import com.example.cook_ford.domain.repository.AuthRepositoryImpl
import com.example.cook_ford.data.remote.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepository(
    private val apiService: ApiService,
    //private val preferences: AuthPreferences
) : AuthRepositoryImpl, BaseRepo() {

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