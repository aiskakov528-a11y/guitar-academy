package com.guitaracademy.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.guitaracademy.app.ui.screens.HomeScreen
import com.guitaracademy.app.ui.screens.PlaceholderScreen

fun NavController.navigateTopLevel(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun GuitarAcademyApp() {
    val nav = rememberNavController()
    val backStack by nav.currentBackStackEntryAsState()
    val current = backStack?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                Dest.bottomBar.forEach { d ->
                    NavigationBarItem(
                        selected = current == d.route,
                        onClick = { nav.navigateTopLevel(d.route) },
                        icon = { Icon(d.icon, contentDescription = d.label) },
                        label = { Text(d.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = nav,
            startDestination = Dest.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Dest.Home.route) {
                HomeScreen(onNavigate = { nav.navigateTopLevel(it) })
            }
            composable(Dest.Lessons.route) {
                PlaceholderScreen("Уроки", "Курс из десяти этапов появится в следующих шагах разработки.")
            }
            composable(Dest.Tuner.route) {
                PlaceholderScreen("Тюнер", "Хроматический тюнер на микрофоне появится в следующих шагах разработки.")
            }
            composable(Dest.Metronome.route) {
                PlaceholderScreen("Метроном", "Метроном (30–250 BPM) появится в следующих шагах разработки.")
            }
            composable(Dest.Progress.route) {
                PlaceholderScreen("Прогресс", "Статистика и достижения появятся после появления уроков.")
            }
        }
    }
}
