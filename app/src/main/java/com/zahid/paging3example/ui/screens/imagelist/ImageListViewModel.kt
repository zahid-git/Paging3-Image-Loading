package com.zahid.paging3example.ui.screens.imagelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.zahid.paging3example.data.datasource.DataResult
import com.zahid.paging3example.data.datasource.model.ImageListModel
import com.zahid.paging3example.domain.repository.ImageRepository
import com.zahid.paging3example.domain.usecase.ImageLoadingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    imageUseCase: ImageLoadingUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(ImageListViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<ImageListViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    val getImageList = imageUseCase.fetchImages().cachedIn(viewModelScope)

    fun refreshImages() =
        viewModelScope.launch {
            _viewEvent.emit(ImageListViewEvent.refreshData)
    }

}