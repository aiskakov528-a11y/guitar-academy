package com.guitaracademy.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = WoodMid,
    onPrimary = Cream,
    primaryContainer = CreamVariant,
    onPrimaryContainer = WoodDark,
    secondary = Terracotta,
    onSecondary = Cream,
    tertiary = WoodLight,
    background = Cream,
    onBackground = Espresso,
    surface = Cream,
    onSurface = Espresso,
    surfaceVariant = CreamVariant,
    onSurfaceVariant = WoodDark
)

private val DarkColors = darkColorScheme(
    primary = WoodLight,
    onPrimary = Espresso,
    primaryContainer = EspressoVariant,
    onPrimaryContainer = Cream,
    secondary = Terracotta,
    onSecondary = Cream,
    tertiary = WoodMid,
    background = Espresso,
    onBackground = Cream,
    surface = EspressoSurface,
    onSurface = Cream,
    surfaceVariant = EspressoVariant,
    onSurfaceVariant = CreamVariant
)

@Composable
fun GuitarAcademyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content
    )
}
