package com.zahid.paging3example.data.repository

import com.google.gson.Gson
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
    private val apiService: ApiService,
    private val gson: Gson
) : ImageRepository, NetworkCallback(gson) {


    override suspend fun loadImages(page: Int, limit: Int) : Flow<DataResult<ImageListModel>> = flow{
        try {
            val apiResponse = apiService.fetchImages(page, limit)
            println(apiResponse.body())
            emit(DataResult.OnSuccess(data = ImageListModel()))
        } catch (e: Exception) {}
    }.flowOn(Dispatchers.IO)


}