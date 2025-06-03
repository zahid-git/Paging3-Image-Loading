package com.zahid.paging3example.data.repository

import com.zahid.paging3example.data.datasource.DataResult
import com.zahid.paging3example.data.datasource.model.ImageListModel
import com.zahid.paging3example.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import javax.inject.Inject


class ImageRepositoryImpl @Inject constructor(
    private val apiClient: OkHttpClient
) : ImageRepository {


    override fun loadImages(nextIndex: Int) : Flow<DataResult<ImageListModel>> = flow{
        try {




            emit(DataResult.OnSuccess(message = "", data = ImageListModel()))

        } catch (e: Exception) {}
    }.flowOn(Dispatchers.IO)


}