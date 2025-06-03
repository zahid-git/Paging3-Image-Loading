package com.zahid.paging3example.data.datasource

sealed class DataResult<T> (val message: String?, val data: T?, val code: Int?) {
    class  OnLoading<T> : DataResult<T>(null,null, null)
    class OnSuccess<T>(data: T?) : DataResult<T>(message= null, data= data, null)
    class OnFail<T>(message: String?, data: T?, code: Int?) : DataResult<T>(message= message, data= data, code = code)
}