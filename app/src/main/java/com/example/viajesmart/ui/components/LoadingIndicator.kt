package com.example.viajesmart.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

/**
 * Componente de carga con spinner centrado
 * @param modifier Modificador para personalizar el layout
 * @param color Color opcional para el indicador (por defecto usa el color primario del tema)
 * @param fullScreen Si es true, ocupa toda la pantalla (para cargas de página completa)
 */
@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    fullScreen: Boolean = false
) {
    Box(
        modifier = if (fullScreen) {
            modifier
                .fillMaxSize()
                .zIndex(1f) // Para superponer sobre otros elementos
        } else {
            modifier.fillMaxWidth()
        },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            color = color,
            strokeWidth = 3.dp
        )
    }
}