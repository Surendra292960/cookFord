package com.example.cook_ford.domain.use_cases

import com.example.cook_ford.data.remote.request.SignInRequest
import com.example.cook_ford.data.remote.response.SignInResponse
import com.example.cook_ford.data.repository.AuthRepository
import com.example.cook_ford.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

class SignInUseCase(private val repository: AuthRepository) {
    
    suspend operator fun invoke(signInRequest: SignInRequest): Flow<NetworkResult<SignInResponse>> {
        return repository.signIn(signInRequest)
    }
}