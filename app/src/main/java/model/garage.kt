package com.example.carwash.model

data class Garage(
    val id: Int,
    val name: String,
    val address: String,
    val rating: Double,
    val isAvailable: Boolean,
    val price: Int,
    val services: List<String>
)
