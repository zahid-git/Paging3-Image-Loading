package com.zahid.paging3example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zahid.paging3example.data.datasource.DataResult
import com.zahid.paging3example.data.datasource.ImagePagingSource
import com.zahid.paging3example.data.datasource.model.ImageListModel
import com.zahid.paging3example.data.datasource.remote.ApiService
import com.zahid.paging3example.data.datasource.remote.NetworkCallback
import com.zahid.paging3example.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ImageRepository, NetworkCallback() {

    override suspend fun loadImages(page: Int, limit: Int) : Flow<DataResult<List<ImageListModel>>> = flow{
        try {
            emit(DataResult.OnLoading())
            val apiResponse =  safeAPICall { apiService.fetchImages(page, limit) }
            apiResponse.data?.let {
                emit(DataResult.OnSuccess(data = it.data))
            } ?: run {
                emit(DataResult.OnFail(message = "No Data", data = listOf(), code = null))
            }
        } catch (e: Exception) {
            emit(DataResult.OnFail(message = e.message?.toString(), data = listOf(), code = null))
        }
    }.flowOn(Dispatchers.IO)

    override fun loadImagePaging(): Flow<PagingData<ImageListModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory =  {
                ImagePagingSource(apiService)
            }
        ).flow
    }

}