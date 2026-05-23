package com.example.ui.theme

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.ui.graphics.Color

enum class AppThemeMode {
    SYSTEM, LIGHT, DARK
}

data class PastelTheme(
    val name: String,
    val color: Color
)

object ThemeManager {
    val pastelThemes = listOf(
        PastelTheme("Default", md_theme_light_primary),
        PastelTheme("Lavender", Color(0xFFB39DDB)),
        PastelTheme("Mint", Color(0xFF80CBC4)),
        PastelTheme("Peach", Color(0xFFFFCC80)),
        PastelTheme("Sky", Color(0xFF81D4FA)),
        PastelTheme("Rose", Color(0xFFF48FB1)),
        PastelTheme("Lemon", Color(0xFFFFF59D)),
        PastelTheme("Coral", Color(0xFFFFAB91))
    )

    private val _themeMode = MutableStateFlow(AppThemeMode.SYSTEM)
    val themeMode: StateFlow<AppThemeMode> = _themeMode.asStateFlow()

    private val _currentPastelTheme = MutableStateFlow(pastelThemes[0])
    val currentPastelTheme: StateFlow<PastelTheme> = _currentPastelTheme.asStateFlow()

    fun setThemeMode(mode: AppThemeMode) {
        _themeMode.value = mode
    }

    fun setPastelTheme(theme: PastelTheme) {
        _currentPastelTheme.value = theme
    }
}
