package com.example.proyectofinalmovilescalculosfinacieros.model

data class CalculationResult(
    val title: String,
    val result: String,
    val inputs: Map<String, String>
)