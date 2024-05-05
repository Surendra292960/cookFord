package com.example.cook_ford.domain.use_cases

import com.example.cook_ford.data.remote.request.SignUpRequest
import com.example.cook_ford.data.remote.response.SignUpResponse
import com.example.cook_ford.data.repository.AuthRepository
import com.example.cook_ford.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

class SignUpUseCase (private val repository: AuthRepository) {

    suspend operator fun invoke(signUpRequest: SignUpRequest): Flow<NetworkResult<SignUpResponse>> {
        return repository.signUp(signUpRequest)
    }
}