package com.example.cook_ford.data.repository
import com.example.cook_ford.data.ApiService
import com.example.cook_ford.data.remote.BaseRepo
import com.example.cook_ford.data.remote.CuisineResponse
import com.example.cook_ford.data.remote.LanguagesResponse
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.TimeSlotsResponse
import com.example.cook_ford.data.remote.profile_request.ProfileRequest
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepositoryImpl(
    private val apiService: ApiService,
    //private val preferences: AuthPreferences
) : AuthRepository, BaseRepo() {

    override suspend fun getProfiles(): Flow<NetworkResult<List<ProfileResponse>>> {
        return flow {
            emit(safeApiCall { apiService.getProfileRequest() })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getProfileById(authToken: String, profileId: String): Flow<NetworkResult<ProfileResponse>> {
        return flow {
            emit(safeApiCall { apiService.getProfileById(authToken, profileId) })
        }.flowOn(Dispatchers.IO)
    }


    override suspend fun getProviderProfileById(authToken: String, profileId: String): Flow<NetworkResult<ProfileResponse>> {
        return flow {
            emit(safeApiCall { apiService.getProviderProfileById(authToken, profileId) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTimeSlots(): Flow<NetworkResult<TimeSlotsResponse>> {
        return flow {
            emit(safeApiCall { apiService.getTimeSlots() })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getLanguages(): Flow<NetworkResult<LanguagesResponse>> {
        return flow {
            emit(safeApiCall { apiService.getLanguages() })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCuisines(): Flow<NetworkResult<CuisineResponse>> {
        return flow {
            emit(safeApiCall { apiService.getCuisines() })
        }.flowOn(Dispatchers.IO)
    }
}