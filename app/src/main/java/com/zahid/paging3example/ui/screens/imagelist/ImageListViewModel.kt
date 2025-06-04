package com.zahid.paging3example.ui.screens.imagelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.zahid.paging3example.data.datasource.DataResult
import com.zahid.paging3example.data.datasource.model.ImageListModel
import com.zahid.paging3example.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    init {
        fetchImageList()
    }

    private val _viewState = MutableStateFlow(ImageListViewState())
    val viewState = _viewState.asStateFlow()

    val getImageList = imageRepository.loadImagePaging().cachedIn(viewModelScope)

    fun onEvent(event: ImageListViewEvent) {
        when (event) {
            ImageListViewEvent.GetData -> {

            }
        }
    }

    fun fetchImageList(){
        viewModelScope.launch {
            imageRepository.loadImages( 1, 10).collect { dataResult ->
                when(dataResult) {
                    is DataResult.OnLoading<List<ImageListModel>> -> {
                        _viewState.value = _viewState.value.copy(dataFetching = true)
                    }
                    is DataResult.OnSuccess<List<ImageListModel>> -> {
                        _viewState.value = _viewState.value.copy(imageList = viewState.value.imageList + dataResult.data!!, dataFetching = false)
                    }
                    is DataResult.OnFail<List<ImageListModel>> -> {
                        _viewState.value = _viewState.value.copy( dataFetching = false, errorMessage = dataResult.message)
                    }
                }
            }
        }
    }



}