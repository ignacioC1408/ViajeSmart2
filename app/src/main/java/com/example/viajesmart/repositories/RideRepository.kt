package com.example.viajesmart.repositories

import com.example.viajesmart.models.RideOption

class RideRepository {
    suspend fun getAvailableRides(origin: String, destination: String): List<RideOption> {
        // Simulación de datos ficticios
        return listOf(
            RideOption("Cabify", 750.0, "15 min", destination),
            RideOption("Didi", 620.0, "13 min", destination),
            RideOption("UberX", 700.0, "14 min", destination),
            RideOption("Uber Black", 1100.0, "18 min", destination)
        )
    }
}
