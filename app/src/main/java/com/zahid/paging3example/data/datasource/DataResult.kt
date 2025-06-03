package com.zahid.paging3example.data.datasource

sealed class DataResult<T> (message: String?, data: T?, code: Int?) {
    object OnLoading : DataResult<Any>(null,null, null)
    class OnSuccess<T>(message: String, data: T?) : DataResult<T>(message= message, data= data, null)
    class OnFail<T>(message: String, data: T?, code: Int?) : DataResult<T>(message= message, data= data, code = code)
}