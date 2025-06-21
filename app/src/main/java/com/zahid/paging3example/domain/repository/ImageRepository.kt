package com.zahid.paging3example.domain.repository

import androidx.paging.PagingData
import com.zahid.paging3example.data.datasource.DataResult
import com.zahid.paging3example.data.datasource.model.ImageListModel
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun loadImagePaging(pageSize: Int, enablePlaceHolders: Boolean, prefetchDistance: Int, initialLoadSize: Int, maxCacheSize: Int) : Flow<PagingData<ImageListModel>>
}