// app/src/main/java/com/example/viajesmart/models/RideOption.kt
package com.example.viajesmart.models

data class RideOption(
    val serviceName: String,
    val departure: String,
    val destination: String,
    val estimatedTime: String,
    val price: String
)