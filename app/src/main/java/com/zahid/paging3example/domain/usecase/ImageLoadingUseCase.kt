package com.zahid.paging3example.domain.usecase

import androidx.paging.PagingData
import com.zahid.paging3example.data.datasource.model.ImageListModel
import com.zahid.paging3example.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageLoadingUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {

    fun fetchImages(): Flow<PagingData<ImageListModel>> {
        return imageRepository.getImages(
            pageSize = 20,
            enablePlaceHolders = false,
            prefetchDistance = 10,
            initialLoadSize = 20,
            maxCacheSize = 2000
        )
    }

}