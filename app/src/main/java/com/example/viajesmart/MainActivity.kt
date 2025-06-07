package com.example.viajesmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.viajesmart.models.RideOption
import com.example.viajesmart.ui.theme.*
import com.example.viajesmart.viewmodels.RideViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: RideViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViajeSmartTheme {
                CompositionLocalProvider(
                    LocalLifecycleOwner provides this
                ) {
                    AppScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun AppScreen(viewModel: RideViewModel) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val rideOptions by viewModel.rideOptions.collectAsStateWithLifecycle(
        lifecycle = lifecycleOwner.lifecycle,
        initialValue = emptyList()
    )

    val currentLocation by viewModel.currentLocation.collectAsStateWithLifecycle(
        lifecycle = lifecycleOwner.lifecycle,
        initialValue = null
    )

    val currentTime by viewModel.currentTime.collectAsStateWithLifecycle(
        lifecycle = lifecycleOwner.lifecycle,
        initialValue = ""
    )

    var destination by rememberSaveable { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize(), color = Background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = currentTime,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = OnBackground.copy(alpha = 0.7f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "ViajeSmart",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp),
                color = OnBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Ubicación actual",
                    tint = Primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = currentLocation?.let { "${it.latitude}, ${it.longitude}" } ?: "Obteniendo ubicación...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OnBackground.copy(alpha = 0.7f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = destination,
                onValueChange = { destination = it },
                label = { Text("Introduce la dirección de destino") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Surface, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = OnSurface.copy(alpha = 0.3f),
                    focusedLabelColor = Primary,
                    unfocusedLabelColor = OnSurface.copy(alpha = 0.5f),
                    cursorColor = Primary
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.searchRides("Ubicación actual", destination)
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .height(48.dp),
                enabled = destination.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = OnPrimary
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Buscar viajes",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(rideOptions) { ride ->
                    RideOptionCard(ride = ride)
                }
            }
        }
    }
}

@Composable
fun RideOptionCard(ride: RideOption) {
    // Asignar colores basados en el servicio
    val serviceColor = when(ride.serviceName) {
        "Cabify" -> Color(0xFF673AB7)
        "Didi" -> Color(0xFFFF5722)
        "UberX" -> Color(0xFF2196F3) // Azul oscuro para UberX
        "Uber Black" -> Color(0xFF000000) // Negro para Uber Black
        else -> Secondary
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Imagen de servicio con color del tema
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(serviceColor)
                ) {
                    Image(
                        painter = painterResource(id = ride.imageResId),
                        contentDescription = ride.serviceName,
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = ride.serviceName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = OnSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = ride.estimatedTime,
                        style = MaterialTheme.typography.bodyMedium,
                        color = OnSurface.copy(alpha = 0.7f)
                    )
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "$${ride.price.toInt()}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = OnSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.width(120.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = serviceColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Solicitar",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}