package com.example.ui

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Player : Screen("player/{videoUri}") {
        fun createRoute(videoUri: String) = "player/${android.net.Uri.encode(videoUri)}"
    }
}
