package com.zahid.paging3example.ui.screens.imagelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.zahid.paging3example.ui.theme.Paging3ExampleTheme

@Composable
fun ShowHomePage(
    viewModel: ImageListViewModel = hiltViewModel()
){
    val viewStateData by viewModel.viewState.collectAsStateWithLifecycle()

    ShowMainHomePage(
        viewStateData,
        viewModel::onEvent
    )
}

@Composable
fun ShowMainHomePage(
    viewStateData: ImageListViewState,
    action: (ImageListViewEvent)-> Unit
){
    Paging3ExampleTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(viewStateData.imageList.size) {position->
                    var itemValue = viewStateData.imageList[position]

                    Card (
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(Color.Transparent)
                    ) {
                        Text(
                            text = itemValue.author.toString()
                        )

                        itemValue?.download_url?.let {
                            AsyncImage(
                                modifier = Modifier.size(100.dp),
                                model = itemValue.download_url.toString(),
                                contentDescription = "Selfie Image",
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowHomePagePreview(){
    ShowMainHomePage(
        viewStateData = ImageListViewState(),
        {}
    )
}

