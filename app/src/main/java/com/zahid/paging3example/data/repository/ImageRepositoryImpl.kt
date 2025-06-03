package com.zahid.paging3example.data.repository

import com.zahid.paging3example.data.datasource.DataResult
import com.zahid.paging3example.data.datasource.model.ImageListModel
import com.zahid.paging3example.data.datasource.remote.ApiService
import com.zahid.paging3example.data.datasource.remote.NetworkCallback
import com.zahid.paging3example.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ImageRepository, NetworkCallback() {

    override suspend fun loadImages(page: Int, limit: Int) : Flow<DataResult<List<ImageListModel>>> = flow{
        try {
            emit(DataResult.OnLoading())
            val apiResponse = apiService.fetchImages(page, limit)
            apiResponse.body()?.let {
                emit(DataResult.OnSuccess(data = it))
            } ?: run {
                emit(DataResult.OnFail(message = "No Data", data = listOf(), code = null))
            }
        } catch (e: Exception) {
            emit(DataResult.OnFail(message = e.message?.toString(), data = listOf(), code = null))
        }
    }.flowOn(Dispatchers.IO)

}