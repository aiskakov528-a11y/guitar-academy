package com.guitaracademy.app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Timer
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Dest(val route: String, val label: String, val icon: ImageVector) {
    data object Home : Dest("home", "Главная", Icons.Filled.Home)
    data object Lessons : Dest("lessons", "Уроки", Icons.AutoMirrored.Filled.MenuBook)
    data object Tuner : Dest("tuner", "Тюнер", Icons.Filled.Mic)
    data object Metronome : Dest("metronome", "Метроном", Icons.Filled.Timer)
    data object Progress : Dest("progress", "Прогресс", Icons.Filled.EmojiEvents)

    companion object {
        val bottomBar = listOf(Home, Lessons, Tuner, Metronome, Progress)
    }
}
