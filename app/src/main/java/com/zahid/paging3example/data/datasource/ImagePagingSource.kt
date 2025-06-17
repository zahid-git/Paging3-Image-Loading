package com.zahid.paging3example.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zahid.paging3example.data.datasource.model.ImageListModel
import com.zahid.paging3example.data.datasource.remote.ApiService

class ImagePagingSource(
    private val apiService: ApiService
) : PagingSource<Int, ImageListModel>() {

    private val numOfOffScreenPage: Int = 4

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageListModel> {
        val pageIndex = params.key ?: 1
        val pageSize = params.loadSize
        return try {
            val responseData = apiService.fetchImages(pageIndex, pageSize)

            LoadResult.Page(
                data = responseData.body()!!,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = if (responseData.body()!!.isEmpty()) null else pageIndex + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageListModel>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(numOfOffScreenPage)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(numOfOffScreenPage)
        }
    }

}