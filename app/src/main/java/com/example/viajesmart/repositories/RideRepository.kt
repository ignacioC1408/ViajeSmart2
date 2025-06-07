package com.example.viajesmart.repositories

import com.example.viajesmart.R
import com.example.viajesmart.models.RideOption

class RideRepository {
    suspend fun getAvailableRides(origin: String, destination: String): List<RideOption> {
        return listOf(
            RideOption(
                "Cabify",
                750.0,
                "15 min",
                destination,
                R.drawable.ic_cabify
            ),
            RideOption(
                "Didi",
                620.0,
                "13 min",
                destination,
                R.drawable.ic_didi
            ),
            RideOption(
                "UberX",
                700.0,
                "14 min",
                destination,
                R.drawable.ic_uberx
            ),
            RideOption(
                "Uber Black",
                1100.0,
                "18 min",
                destination,
                R.drawable.ic_uber_black
            )
        )
    }
}