package com.zahid.paging3example.data.datasource.remote

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.zahid.paging3example.data.datasource.DataResult
import com.zahid.paging3example.data.datasource.model.BaseDataModel
import retrofit2.Response
import java.net.HttpURLConnection

abstract class NetworkCallback {

    inline fun <reified T : Any> safeAPICall(apiCall: () -> Response<T>): DataResult<BaseDataModel<T>> {
        return try {
            val apiResponse = apiCall()
            if (apiResponse.isSuccessful) {
                apiResponse.body()?.let { response ->
                    val gsonParser = Gson()
                    val jsonElement: JsonElement =
                        JsonParser.parseString(gsonParser.toJson(response))

                    if (jsonElement.isJsonArray) {
                        val listType = object : TypeToken<T>() {}.type
                        val responseData: T = gsonParser.fromJson(jsonElement, listType)
                        DataResult.OnSuccess(
                            data = BaseDataModel(
                                status = true,
                                message = "Success",
                                data = responseData
                            )
                        )
                    } else {
                        val responseData =
                            gsonParser.fromJson(gsonParser.toJson(response), T::class.java)
                        DataResult.OnSuccess(
                            data = BaseDataModel(
                                status = true,
                                message = "Success",
                                data = responseData
                            )
                        )
                    }
                } ?: run {
                    apiResponse.errorBody().let { errorResponse ->
                        val gsonParser = Gson()
                        val errorResponseData =
                            gsonParser.fromJson(gsonParser.toJson(errorResponse), T::class.java)
                        DataResult.OnFail(data = null, message = "Fail to load data", code = null)
                    }
                }
            } else if (apiResponse.code() == HttpURLConnection.HTTP_BAD_REQUEST) {
                DataResult.OnFail(data = null, message = "Bad Request", code = apiResponse.code())
            } else if (apiResponse.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                DataResult.OnFail(data = null, message = "Unauthorized", code = apiResponse.code())
            } else if (apiResponse.code() == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                DataResult.OnFail(
                    data = null,
                    message = "Internal Error",
                    code = apiResponse.code()
                )
            } else {
                DataResult.OnFail(
                    data = null,
                    message = "Error Occurred",
                    code = apiResponse.code()
                )
            }
        } catch (e: Exception) {
            DataResult.OnFail(data = null, message = e.message.toString(), code = null)
        }
    }
}