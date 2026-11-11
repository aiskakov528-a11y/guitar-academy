package com.guitaracademy.app.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guitaracademy.app.ui.Dest

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    // Пока уроков и статистики нет, реальный прогресс равен нулю.
    val coursePercent = 0f
    val dailyGoalMinutes = 15
    val minutesToday = 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.MusicNote,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(32.dp)
            )
            Text(
                "Guitar Academy",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Text(
            "Добро пожаловать! Давайте научимся играть на классической гитаре.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Следующий урок", style = MaterialTheme.typography.labelLarge)
                Text("Этап 1 · Устройство классической гитары", style = MaterialTheme.typography.titleLarge)
                Button(onClick = { onNavigate(Dest.Lessons.route) }) {
                    Icon(Icons.Filled.PlayArrow, contentDescription = null)
                    Text("Начать", modifier = Modifier.padding(start = 4.dp))
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ProgressRing(progress = coursePercent)
                    Text("Курс пройден", style = MaterialTheme.typography.labelLarge)
                }
            }
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Column {
                        Text("Уровень", style = MaterialTheme.typography.labelMedium)
                        Text("Новичок", style = MaterialTheme.typography.titleLarge)
                    }
                    Column {
                        Text("Дней обучения", style = MaterialTheme.typography.labelMedium)
                        Text("0", style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        }

        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Цель на сегодня", style = MaterialTheme.typography.labelLarge)
                Text("$minutesToday из $dailyGoalMinutes мин", style = MaterialTheme.typography.titleLarge)
                LinearProgressIndicator(
                    progress = { minutesToday.toFloat() / dailyGoalMinutes },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            QuickCard("Тюнер", Modifier.weight(1f)) { onNavigate(Dest.Tuner.route) }
            QuickCard("Метроном", Modifier.weight(1f)) { onNavigate(Dest.Metronome.route) }
        }
    }
}

@Composable
private fun QuickCard(title: String, modifier: Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Box(Modifier.fillMaxWidth().padding(20.dp), contentAlignment = Alignment.Center) {
            Text(
                title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun ProgressRing(progress: Float, modifier: Modifier = Modifier, ringSize: Dp = 96.dp) {
    val track = MaterialTheme.colorScheme.background
    val color = MaterialTheme.colorScheme.secondary
    val animated by animateFloatAsState(targetValue = progress, label = "ring")
    Box(modifier.size(ringSize), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val strokeWidth = 10.dp.toPx()
            val inset = strokeWidth / 2
            val arcSize = Size(this.size.width - strokeWidth, this.size.height - strokeWidth)
            drawArc(
                color = track, startAngle = 0f, sweepAngle = 360f, useCenter = false,
                topLeft = Offset(inset, inset), size = arcSize,
                style = Stroke(strokeWidth, cap = StrokeCap.Round)
            )
            drawArc(
                color = color, startAngle = -90f, sweepAngle = 360f * animated, useCenter = false,
                topLeft = Offset(inset, inset), size = arcSize,
                style = Stroke(strokeWidth, cap = StrokeCap.Round)
            )
        }
        Text("${(progress * 100).toInt()}%", style = MaterialTheme.typography.titleLarge)
    }
}
