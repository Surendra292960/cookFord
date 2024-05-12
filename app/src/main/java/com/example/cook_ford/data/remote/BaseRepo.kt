package com.example.cook_ford.data.remote

import com.example.cook_ford.utils.AppConstants
import org.json.JSONObject
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
			val errorResponse = convertErrorBody( response)
			return error(errorResponse, response.isSuccessful)
		} catch (e: IOException) {
			return error(errorMessage = e.message ?: AppConstants.PLEASE_CHECK_INTERNET, status = false)
		}
		catch (e: Exception) {
			return error(errorMessage = e.message ?: e.toString(), status = false)
		}
	}
	private fun <T> error(errorMessage: String, status:Boolean): NetworkResult<T> = NetworkResult.Error(errorMessage, status = status)

	private fun<T> convertErrorBody(response: Response<T>):String{
		var message=AppConstants.EMPTY_STRING
		try {
			val errorObj = JSONObject(response.errorBody()!!.string())
			message = errorObj.getString(AppConstants.ERROR)
		}catch (e:Exception){
			e.printStackTrace()
		}
		return message
	}
}

//TODO Please handle 404 error
//TODO server not start error


