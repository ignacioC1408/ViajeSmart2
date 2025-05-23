

package com.example.viajesmart.repositories

import com.example.viajesmart.models.RideOption

class RideRepository {
    suspend fun getAvailableRides(origin: String, destination: String): List<RideOption> {
        // Simulación de datos ficticios
        return listOf(
            RideOption(
                "Cabify", destination, "10 min", "750",
                price = TODO()
            ),
            RideOption(
                "Didi", destination, "12 min", "620",
                price = TODO()
            ),
            RideOption(
                "UberX", destination, "9 min", "700",
                price = TODO()
            ),
            RideOption(
                "Uber Black", destination, "7 min", "1100",
                price = TODO()
            )
        )
    }
}
