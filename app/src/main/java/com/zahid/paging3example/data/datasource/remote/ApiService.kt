package com.zahid.paging3example.data.datasource.remote

import com.zahid.paging3example.data.datasource.model.ImageListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("list")
    suspend fun fetchImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<List<ImageListModel>>


}