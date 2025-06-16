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

    private val _viewState = MutableStateFlow(ImageListViewState())
    val viewState = _viewState.asStateFlow()

    val getImageList = imageRepository.loadImagePaging().cachedIn(viewModelScope)

    fun onEvent(event: ImageListViewEvent) {
        when (event) {
            ImageListViewEvent.GetData -> {

            }
        }
    }

}