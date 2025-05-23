package com.example.viajesmart.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viajesmart.viewmodels.RideViewModel

@Composable
fun SearchBar(
    viewModel: RideViewModel,
    currentLocation: String,
    modifier: Modifier = Modifier
) {
    var departure by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Campo de origen
        OutlinedTextField(
            value = departure,
            onValueChange = { departure = it },
            label = { Text("Origen") },
            placeholder = { Text(currentLocation) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Campo de destino
        OutlinedTextField(
            value = destination,
            onValueChange = { destination = it },
            label = { Text("Destino") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de búsqueda
        Button(
            onClick = {
                if (departure.isNotBlank() && destination.isNotBlank()) {
                    viewModel.searchRides(departure, destination)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = departure.isNotBlank() && destination.isNotBlank()
        ) {
            Text(
                text = "Buscar viajes",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}