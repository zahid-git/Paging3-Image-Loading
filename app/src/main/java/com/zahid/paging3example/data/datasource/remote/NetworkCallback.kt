package com.zahid.paging3example.data.datasource.remote

import com.google.gson.Gson
import com.zahid.paging3example.data.datasource.DataResult
import com.zahid.paging3example.data.datasource.model.BaseDataModel
import retrofit2.Response
import java.net.HttpURLConnection
import javax.inject.Inject

abstract class NetworkCallback @Inject constructor(val gsonParser: Gson) {
    fun <T> success(response: T?): DataResult<T> = DataResult.OnSuccess(data = response)
    fun <T> fail(response: T?, message: String, code: Int?) : DataResult<T> = DataResult.OnFail(
        message = message, data = response, code = code
    )

    inline fun <reified T: Any> safeAPICall( apiCall: ()-> Response<T>) : DataResult<BaseDataModel<T>> {
        return try {
            val apiResponse = apiCall()
            if(apiResponse.isSuccessful) {
                apiResponse.body()?.let { response ->
                    val responseData = gsonParser.fromJson(gsonParser.toJson(response), T::class.java)
                    success(response = BaseDataModel(status = true, message = "Success", data = responseData))
                } ?: run {
                    apiResponse.errorBody().let { errorResponse ->
                        val errorResponseData = gsonParser.fromJson(gsonParser.toJson(errorResponse), T::class.java)
                        fail(null,"Fail to load data", null)
                    }
                }
            } else if(apiResponse.code() == HttpURLConnection.HTTP_BAD_REQUEST){
                fail(message = "Bad Request", response = null, code = apiResponse.code())
            }else if(apiResponse.code() == HttpURLConnection.HTTP_UNAUTHORIZED){
                fail(message = "Unauthorized", response = null, code = apiResponse.code())
            }else if (apiResponse.code() == HttpURLConnection.HTTP_INTERNAL_ERROR){
                fail(message = "Internal Error", response = null, code = apiResponse.code())
            } else {
                fail(message = "Error Occurred", response = null, code = apiResponse.code())
            }
        } catch (e: Exception) {
            fail(message = e.message.toString(), response = null, code = null)
        }
    }
}