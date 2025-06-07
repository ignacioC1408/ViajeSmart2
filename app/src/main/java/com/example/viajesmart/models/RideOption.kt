package com.example.viajesmart.models

data class RideOption(
    val serviceName: String,
    val price: Double,
    val estimatedTime: String,
    val destination: String,
    val imageResId: Int
)