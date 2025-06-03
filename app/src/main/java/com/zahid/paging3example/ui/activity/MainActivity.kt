package com.zahid.paging3example.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.zahid.paging3example.ui.screens.imagelist.ImageListViewModel
import com.zahid.paging3example.ui.screens.imagelist.ShowHomePage
import com.zahid.paging3example.ui.theme.Paging3ExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Paging3ExampleTheme {
                ShowHomePage( )
            }
        }
    }
}