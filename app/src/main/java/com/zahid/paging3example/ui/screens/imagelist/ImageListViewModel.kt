package com.zahid.paging3example.ui.screens.imagelist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zahid.paging3example.data.datasource.DataResult
import com.zahid.paging3example.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {



    var viewState by mutableStateOf(ImageListViewState())
        private set

    fun onEvent(event: ImageListViewEvent) {
        when (event) {
            ImageListViewEvent.GetData -> {

            }
        }
    }


    fun fetchImageList(){
        viewModelScope.launch {
            imageRepository.loadImages( 1, 10).collect { dataResult->
                when(dataResult) {
                    is DataResult.OnFail<*> -> TODO()
                    DataResult.OnLoading -> TODO()
                    is DataResult.OnSuccess<*> -> TODO()
                }
            }
        }
    }

}