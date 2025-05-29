package com.example.viajesmart.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viajesmart.models.RideOption
import com.example.viajesmart.repositories.RideRepository
import com.example.viajesmart.ui.components.FilterOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

data class Location(val latitude: Double, val longitude: Double)

class RideViewModel : ViewModel() {
    private val repository = RideRepository()

    private val _rideOptions = MutableStateFlow<List<RideOption>>(emptyList())
    val rideOptions: StateFlow<List<RideOption>> = _rideOptions

    private val _currentLocation = MutableStateFlow<Location?>(Location(-34.6037, -58.3816))
    val currentLocation: StateFlow<Location?> = _currentLocation

    private val _currentTime = MutableStateFlow("")
    val currentTime: StateFlow<String> = _currentTime

    private val _currentFilter = MutableStateFlow<FilterOption?>(null)
    val currentFilter: StateFlow<FilterOption?> = _currentFilter

    init {
        updateTime()
    }

    private fun updateTime() {
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        _currentTime.value = timeFormat.format(Date())
    }

    fun searchRides(origin: String, destination: String) {
        viewModelScope.launch {
            val rides = repository.getAvailableRides(origin, destination)
            _rideOptions.value = rides
        }
    }

    fun applyFilter(filter: FilterOption) {
        _currentFilter.value = filter
    }
}