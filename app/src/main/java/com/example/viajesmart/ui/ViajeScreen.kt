package com.example.viajesmart.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun ViajeScreen() {
    val rideOptions = listOf(
        RideOption("Uber", "9,50"),
        RideOption("Cabify", "8,75"),
        RideOption("DiDi", "10,20")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Hora actual (parte superior derecha)
        Text(
            text = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")),
            modifier = Modifier.align(Alignment.End),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título de la app
        Text(
            text = "ViajeSmart",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo de búsqueda
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Introduce la dirección de destino") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Lista de opciones de viaje
        rideOptions.forEach { ride ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = ride.serviceName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )

                Button(
                    onClick = {},
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Solicitar\n$${ride.price}")
                }
            }
        }
    }
}

data class RideOption(
    val serviceName: String,
    val price: String
)