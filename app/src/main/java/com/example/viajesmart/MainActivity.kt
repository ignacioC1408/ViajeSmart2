package com.example.viajesmart

import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.viajesmart.models.RideOption
import com.example.viajesmart.ui.theme.ViajeSmartTheme
import com.example.viajesmart.viewmodels.RideViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: RideViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViajeSmartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun AppScreen(viewModel: RideViewModel) {
    val currentLocation by viewModel.currentLocation.collectAsStateWithLifecycle()
    val rideOptions by viewModel.rideOptions.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    RideApp(
        currentLocation = currentLocation,
        rideOptions = rideOptions,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onSearch = { departure, destination ->
            viewModel.searchRides(departure, destination)
        }
    )
}

@Composable
fun RideApp(
    currentLocation: Location?,
    rideOptions: List<RideOption>,
    isLoading: Boolean,
    errorMessage: String?,
    onSearch: (String, String) -> Unit
) {
    var departure by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    LaunchedEffect(errorMessage) {
        if (!errorMessage.isNullOrEmpty()) {
            showError = true
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = departure,
            onValueChange = { departure = it },
            label = { Text("Lugar de salida") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = destination,
            onValueChange = { destination = it },
            label = { Text("Lugar de destino") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (departure.isNotBlank() && destination.isNotBlank()) {
                    onSearch(departure, destination)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = departure.isNotBlank() && destination.isNotBlank()
        ) {
            Text("Buscar viajes")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            showError && !errorMessage.isNullOrEmpty() -> {
                ErrorMessage(message = errorMessage) {
                    showError = false
                }
            }
            rideOptions.isNotEmpty() -> RideOptionsList(rideOptions = rideOptions)
            else -> Text(
                text = "Ingrese lugares de salida y destino",
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}

@Composable
fun ErrorMessage(message: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Error") },
        text = { Text(message) },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@Composable
fun RideOptionsList(rideOptions: List<RideOption>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(rideOptions) { option ->
            RideOptionCard(option = option)
        }
    }
}

@Composable
fun RideOptionCard(option: RideOption) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = option.serviceName,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "De: ${option.departure}")
            Text(text = "A: ${option.destination}")
            Text(text = "Tiempo estimado: ${option.estimatedTime}")
            Text(text = "Precio: ${option.price}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRideApp() {
    ViajeSmartTheme {
        RideApp(
            currentLocation = null,
            rideOptions = emptyList(),
            isLoading = false,
            errorMessage = null,
            onSearch = { _, _ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRideAppWithOptions() {
    ViajeSmartTheme {
        RideApp(
            currentLocation = null,
            rideOptions = listOf(
                RideOption(
                    serviceName = "UberX",
                    departure = "Centro",
                    destination = "Aeropuerto",
                    estimatedTime = "15 min",
                    price = "$12.50"
                ),
                RideOption(
                    serviceName = "Didi",
                    departure = "Centro",
                    destination = "Aeropuerto",
                    estimatedTime = "10 min",
                    price = "$10.00"
                )
            ),
            isLoading = false,
            errorMessage = null,
            onSearch = { _, _ -> }
        )
    }
}