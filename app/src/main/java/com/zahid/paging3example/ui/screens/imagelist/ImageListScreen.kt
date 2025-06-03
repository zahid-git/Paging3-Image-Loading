package com.zahid.paging3example.ui.screens.imagelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.zahid.paging3example.ui.theme.Paging3ExampleTheme

@Composable
fun ShowHomePage(
    viewModel: ImageListViewModel = hiltViewModel()
){
    ShowMainHomePage(
        viewModel.viewState,
        viewModel::onEvent
    )
}

@Composable
fun ShowMainHomePage(
    pageDataModel: ImageListViewState,
    action: (ImageListViewEvent)-> Unit
){
    Paging3ExampleTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)

        ) {

        }
    }
}

@Preview
@Composable
fun ShowHomePagePreview(){
    ShowMainHomePage(
        pageDataModel = ImageListViewState("1"),
        {}
    )
}

