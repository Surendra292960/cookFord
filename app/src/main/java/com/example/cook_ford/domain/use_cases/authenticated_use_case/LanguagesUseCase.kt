package com.example.cook_ford.domain.use_cases.authenticated_use_case

import com.example.cook_ford.data.remote.LanguagesResponse
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.repository.AuthRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LanguagesUseCase @Inject constructor(private val repository: AuthRepositoryImpl) {

    suspend operator fun invoke(): Flow<NetworkResult<LanguagesResponse>> {
        return repository.getLanguages()
    }
}