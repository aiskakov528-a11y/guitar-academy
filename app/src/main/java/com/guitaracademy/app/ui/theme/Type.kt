package com.guitaracademy.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val base = Typography()

val AppTypography = base.copy(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold,
        fontSize = 32.sp, lineHeight = 40.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold,
        fontSize = 26.sp, lineHeight = 34.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp, lineHeight = 28.sp
    )
)
