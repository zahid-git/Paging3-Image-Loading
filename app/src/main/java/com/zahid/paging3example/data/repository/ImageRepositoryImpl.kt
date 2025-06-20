package com.zahid.paging3example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zahid.paging3example.data.datasource.ImagePagingSource
import com.zahid.paging3example.data.datasource.model.ImageListModel
import com.zahid.paging3example.data.datasource.remote.ApiService
import com.zahid.paging3example.data.datasource.remote.NetworkCallback
import com.zahid.paging3example.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ImageRepository, NetworkCallback() {

    override fun loadImagePaging(): Flow<PagingData<ImageListModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 15,
                initialLoadSize = 20,
                maxSize = 200
            ), pagingSourceFactory =  {
                ImagePagingSource(apiService)
            }
        ).flow
    }

}