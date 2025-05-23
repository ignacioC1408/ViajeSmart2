package com.example.viajesmart.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0066CC),
    secondary = Color(0xFF6C757D),
    tertiary = Color(0xFF17A2B8),
    surface = Color.White,
    surfaceVariant = Color(0xFFF8F9FA),
    onSurface = Color.Black
)

@Composable
fun ViajeSmartTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}