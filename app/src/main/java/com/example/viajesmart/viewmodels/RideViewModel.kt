package com.example.viajesmart.viewmodels

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.viajesmart.models.RideOption
import com.example.viajesmart.repositories.RideRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RideViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RideRepository()

    private val _rideOptions = MutableStateFlow<List<RideOption>>(emptyList())
    val rideOptions: StateFlow<List<RideOption>> = _rideOptions

    private val _currentLocation = MutableStateFlow<Location?>(null)
    val currentLocation: StateFlow<Location?> = _currentLocation

    private val _currentTime = MutableStateFlow("")
    val currentTime: StateFlow<String> = _currentTime

    init {
        updateCurrentTime()
        getCurrentLocation()
    }

    fun searchRides(origin: String, destination: String) {
        viewModelScope.launch {
            val results = repository.getAvailableRides(origin, destination)
            _rideOptions.value = results
        }
    }

    private fun updateCurrentTime() {
        val formatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        _currentTime.value = formatter.format(Date())
    }

    private fun getCurrentLocation() {
        // Simulamos una ubicación por ahora
        val mockLocation = Location("mock").apply {
            latitude = -34.6037 // Ej: Buenos Aires
            longitude = -58.3816
        }
        _currentLocation.value = mockLocation
    }
}
