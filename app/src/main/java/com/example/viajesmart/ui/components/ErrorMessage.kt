package com.example.viajesmart.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.viajesmart.R

@Composable
fun ErrorMessage(
    message: String?,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* No permitir cerrar haciendo clic fuera */ },
        modifier = modifier,
        title = { Text(stringResource(R.string.error_title)) },
        text = { Text(message ?: stringResource(R.string.unknown_error)) },
        confirmButton = {
            Button(onClick = onRetry) {
                Text(stringResource(R.string.retry))
            }
        },
        dismissButton = {
            TextButton(onClick = { /* Acción opcional de cancelar */ }) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}