package com.zahid.paging3example.ui.screens.imagelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.zahid.paging3example.data.datasource.model.ImageListModel
import com.zahid.paging3example.ui.theme.Paging3ExampleTheme
import org.intellij.lang.annotations.JdkConstants


@Composable
fun ShowImageListScreen(
    navController: NavHostController,
    viewModel: ImageListViewModel
) {
    val viewStateData by viewModel.viewState.collectAsStateWithLifecycle()
    val productItems = viewModel.getImageList.collectAsLazyPagingItems()

    ShowMainHomePage(
        viewStateData,
        productItems,
        viewModel::onEvent
    )
}

@Composable
fun ShowMainHomePage(
    viewStateData: ImageListViewState,
    productItems: LazyPagingItems<ImageListModel>?,
    action: (ImageListViewEvent) -> Unit
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
                items(productItems!!.itemCount) {position->
                    var itemValue = productItems[position]
                    Card (
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(Color.Transparent)
                    ) {
                        Box {
                            itemValue?.download_url?.let {
                                AsyncImage(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .aspectRatio(itemValue.width!!*1.0f / itemValue.height!!),
                                    model = itemValue.download_url.toString(),
                                    contentDescription = "Image",
                                    contentScale = ContentScale.Fit
                                )
                            }

                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Gray)
                                    .height(30.dp)
                                    .align(Alignment.BottomStart)
                                    .padding(start = 10.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    color = Color.White,
                                    text = itemValue?.author.toString()
                                )
                            }
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
        productItems = null,
        action = {}
    )
}

