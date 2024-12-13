package com.example.proyectofinalmovilescalculosfinacieros.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.proyectofinalmovilescalculosfinacieros.model.CalculationResult

class HistoryViewModel : ViewModel() {
    private val _history = MutableStateFlow<List<CalculationResult>>(emptyList())
    val history: StateFlow<List<CalculationResult>> get() = _history

    fun addToHistory(calculation: CalculationResult) {
        val newHistory = _history.value.toMutableList()
        newHistory.add(calculation)
        _history.value = newHistory
    }
}