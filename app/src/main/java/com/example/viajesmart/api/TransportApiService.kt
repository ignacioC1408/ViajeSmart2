package com.example.viajesmart.api

import com.example.viajesmart.models.RideOption
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TransportApiService {
    @GET("rides")
    suspend fun getRideOptions(
        @Query("origin") departure: String,
        @Query("destination") destination: String
    ): Response<ApiResponse<List<RideOption>>>
}

data class ApiResponse<T>(
    val success: Boolean,
    val data: T,
    val message: String? = null
)

object TransportApi {
    private const val BASE_URL = "https://tumockapi.com/api/v1/"

    val instance: TransportApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TransportApiService::class.java)
    }
}