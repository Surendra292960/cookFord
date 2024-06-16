package com.example.cook_ford.domain.use_cases

import com.example.cook_ford.data.remote.auth_request.SignUpRequest
import com.example.cook_ford.data.remote.auth_response.SignUpResponse
import com.example.cook_ford.data.repository.AuthRepositoryImpl
import com.example.cook_ford.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: AuthRepositoryImpl) {

    suspend operator fun invoke(signUpRequest: SignUpRequest): Flow<NetworkResult<SignUpResponse>> {
        return repository.signUp(signUpRequest)
    }
}