package com.example.cook_ford.data.remote

import retrofit2.Response
import java.io.IOException

abstract class BaseRepo {

	suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
		try {
			val response = apiCall()
			if (response.isSuccessful) {
				val body = response.body()
				body?.let {
					return NetworkResult.Success(body,response.isSuccessful)
				}
			}
			return error("${response.code()} ${response.message()}", response.isSuccessful)
		} catch (e: IOException) {
			return error(e.message ?: "Please check your network connection", false)
		}
		catch (e: Exception) {
			return error(e.message ?: e.toString(), false)
		}
	}
	private fun <T> error(errorMessage: String, status:Boolean): NetworkResult<T> = NetworkResult.Error("Api call failed $errorMessage", status = status)

}