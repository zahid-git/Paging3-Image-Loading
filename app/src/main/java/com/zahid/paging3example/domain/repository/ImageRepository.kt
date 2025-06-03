package com.zahid.paging3example.domain.repository

import com.zahid.paging3example.data.datasource.DataResult
import com.zahid.paging3example.data.datasource.model.ImageListModel
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun loadImages(page: Int, limit: Int) : Flow<DataResult<List<ImageListModel>>>

}