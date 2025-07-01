package com.zahid.paging3example.ui.screens.imagelist

data class ImageListViewState(
    var isLoading: Boolean = false,
    var isRefreshing: Boolean = false,
    var errorMessage: String? = null
)
