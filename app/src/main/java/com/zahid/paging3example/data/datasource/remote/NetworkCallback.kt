package com.zahid.paging3example.data.datasource.remote

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.zahid.paging3example.data.datasource.DataResult
import com.zahid.paging3example.data.datasource.model.BaseDataModel
import retrofit2.Response
import java.net.HttpURLConnection
import javax.inject.Inject

abstract class NetworkCallback{
    fun <T> fail(response: T?, message: String, code: Int?) : DataResult<T> = DataResult.OnFail(
        message = message, data = response, code = code
    )

    inline fun <reified T: Any> safeAPICall( apiCall: ()-> Response<T>) : DataResult<BaseDataModel<T>> {
        return try {
            val apiResponse = apiCall()
            if(apiResponse.isSuccessful) {
                apiResponse.body()?.let { response ->
                    val gsonParser = Gson()
                    val jsonElement: JsonElement = JsonParser.parseString(response.toString())

                    if(jsonElement.isJsonArray) {
                        val listType = object : TypeToken<T>() {}.type
                        val responseData: T = gsonParser.fromJson(jsonElement, listType)
                        DataResult.OnSuccess(data = BaseDataModel(status = true, message = "Success", data = responseData))
                    } else{
                        val responseData = gsonParser.fromJson(gsonParser.toJson(response), T::class.java)
                        DataResult.OnSuccess(data = BaseDataModel(status = true, message = "Success", data = responseData))
                    }
                } ?: run {
                    apiResponse.errorBody().let { errorResponse ->
                        val gsonParser = Gson()
                        val errorResponseData = gsonParser.fromJson(gsonParser.toJson(errorResponse), T::class.java)
                        fail(response = null, message = "Fail to load data", code = null)
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