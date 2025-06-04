package com.zahid.paging3example.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zahid.paging3example.ui.screens.imagelist.ImageListViewModel
import com.zahid.paging3example.ui.screens.imagelist.ShowImageListScreen
import com.zahid.paging3example.utils.Constants

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRoutes.ImageListScreen,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                    delayMillis = Constants.SCREEN_TRANSITION_DELAY
                )
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                    delayMillis = Constants.SCREEN_TRANSITION_DELAY
                )
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                    delayMillis = Constants.SCREEN_TRANSITION_DELAY
                )
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                    delayMillis = Constants.SCREEN_TRANSITION_DELAY
                )
            )
        }
    ) {
        composable<NavRoutes.ImageListScreen> {
            val viewModel: ImageListViewModel = hiltViewModel()
            ShowImageListScreen(navController = navController, viewModel = viewModel)
        }
    }
}
