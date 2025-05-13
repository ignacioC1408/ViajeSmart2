package com.example.viajesmart.api

import com.example.viajesmart.models.RideOption
import retrofit2.http.GET
import retrofit2.http.Query

interface TransportApiService {
    @GET("rides") // Cambia "rides" por el endpoint real de tu API
    suspend fun getRideOptions(
        @Query("departure") departure: String,
        @Query("destination") destination: String
    ): List<RideOption>
}
