// app/src/main/java/com/example/viajesmart/utils/LocationUtils.kt
package com.example.viajesmart.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*

class LocationManager(context: Context) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val _locationData = MutableLiveData<Location>()
    val locationData: LiveData<Location> = _locationData

    // Guardamos el callback como propiedad para poder removerlo
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            _locationData.value = locationResult.lastLocation
        }
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            10000
        ).apply {
            setMinUpdateIntervalMillis(5000)
        }.build()

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null // Looper, null para usar el hilo principal
        )
    }

    fun stopLocationUpdates() {
        // Pasamos el mismo callback que usamos para registrar
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}