package com.example.cook_ford.domain.use_cases.authenticated_use_case

import com.example.cook_ford.data.remote.CuisineResponse
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.repository.AuthRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CuisineUseCase  @Inject constructor(private val repository: AuthRepositoryImpl) {

    suspend operator fun invoke(): Flow<NetworkResult<CuisineResponse>> {
        return repository.getCuisines()
    }
}