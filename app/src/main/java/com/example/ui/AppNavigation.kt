package com.example.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onVideoClick = { video ->
                    navController.navigate(Screen.Player.createRoute(video.uri.toString()))
                }
            )
        }
        composable(
            route = Screen.Player.route,
            arguments = listOf(navArgument("videoUri") { type = NavType.StringType })
        ) { backStackEntry ->
            val videoUri = backStackEntry.arguments?.getString("videoUri") ?: ""
            PlayerScreen(
                videoUri = videoUri,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
