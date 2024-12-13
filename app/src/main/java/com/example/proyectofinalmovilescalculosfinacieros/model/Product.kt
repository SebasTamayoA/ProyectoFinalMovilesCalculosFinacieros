package com.example.proyectofinalmovilescalculosfinacieros.model

data class Product(
    val priceBase: Double,
    val cost: Double,
    val fixedCosts: Double,
    val variableCost: Double,
    val investment: Double,
    val earnings: Double
)