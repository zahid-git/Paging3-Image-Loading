package com.zahid.paging3example.ui.screens.imagelist

import com.zahid.paging3example.data.datasource.model.ImageListModel

data class ImageListViewState(
    var dataFetching: Boolean = false,
    var imageList: List<ImageListModel> = listOf(),
    var errorMessage: String? = null
)
