package com.example.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onVideoClick = { video ->
                    navController.navigate(Screen.Player.createRoute(video.uri.toString()))
                },
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(onNavigateUp = { navController.navigateUp() })
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
