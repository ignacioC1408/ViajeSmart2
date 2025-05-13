package com.example.viajesmart.viewmodels

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.viajesmart.models.RideOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RideViewModel(application: Application) : AndroidViewModel(application) {
    private val _currentLocation = MutableStateFlow<Location?>(null)
    private val _rideOptions = MutableStateFlow<List<RideOption>>(emptyList())
    private val _isLoading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)

    val currentLocation: StateFlow<Location?> = _currentLocation.asStateFlow()
    val rideOptions: StateFlow<List<RideOption>> = _rideOptions.asStateFlow()
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun updateLocation(location: Location) {
        _currentLocation.value = location
    }

    fun searchRides(departure: String, destination: String) {
        if (departure.isBlank() || destination.isBlank()) {
            _errorMessage.value = "Por favor ingrese lugares de salida y destino válidos"
            return
        }

        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                // Simulación de llamada a API
                kotlinx.coroutines.delay(1500)

                // Validación adicional (simulada)
                if (departure.length < 3 || destination.length < 3) {
                    throw IllegalArgumentException("Los lugares deben tener al menos 3 caracteres")
                }

                _rideOptions.value = listOf(
                    RideOption(
                        serviceName = "UberX",
                        departure = departure,
                        destination = destination,
                        estimatedTime = "15 min",
                        price = "$12.50"
                    ),
                    RideOption(
                        serviceName = "Didi",
                        departure = departure,
                        destination = destination,
                        estimatedTime = "10 min",
                        price = "$10.00"
                    ),
                    RideOption(
                        serviceName = "Cabify",
                        departure = departure,
                        destination = destination,
                        estimatedTime = "12 min",
                        price = "$11.20"
                    )
                )
            } catch (e: Exception) {
                _errorMessage.value = "Error al buscar viajes: ${e.message}"
                _rideOptions.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}