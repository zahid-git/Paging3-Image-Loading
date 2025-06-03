package com.zahid.paging3example.domain.repository

import com.zahid.paging3example.data.datasource.DataResult
import com.zahid.paging3example.data.datasource.model.ImageListModel
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    fun loadImages(nextIndex: Int) : Flow<DataResult<ImageListModel>>

}