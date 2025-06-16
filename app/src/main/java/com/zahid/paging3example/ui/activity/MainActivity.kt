package com.zahid.paging3example.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.zahid.paging3example.navigation.AppNavHost
import com.zahid.paging3example.ui.theme.Paging3ExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Paging3ExampleTheme {
                AppNavHost(modifier = Modifier, navController = rememberNavController())
            }
        }
    }
}
