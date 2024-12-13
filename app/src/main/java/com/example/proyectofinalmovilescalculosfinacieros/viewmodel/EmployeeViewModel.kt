package com.example.proyectofinalmovilescalculosfinacieros.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalmovilescalculosfinacieros.model.CalculationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class EmployeeViewModel(private val historyViewModel: HistoryViewModel) : ViewModel() {
    private val _result = MutableStateFlow("")
    val result: StateFlow<String> get() = _result

    private val numberFormat: NumberFormat = NumberFormat.getNumberInstance(Locale.US).apply {
        maximumFractionDigits = 2
    }

    private fun formatNumber(value: Double): String {
        return numberFormat.format(value)
    }

    private fun formatCurrency(value: Double): String {
        return "$${formatNumber(value)}"
    }

    fun calculateNetSalary(baseSalary: Double) {
        viewModelScope.launch {
            if (baseSalary <= 0) {
                _result.value = "Por favor ingrese un salario válido"
                return@launch
            }
            val deductions = baseSalary * (0.04 + 0.04) // Pensión 4% + Salud 4%
            val result = baseSalary - deductions
            val formattedResult = formatCurrency(result)
            addToHistory(
                "Salario Neto",
                formattedResult,
                mapOf("Salario Base" to formatCurrency(baseSalary))
            )
            _result.value = formattedResult
        }
    }

    fun calculateDaytimeOvertime(baseSalary: Double) {
        viewModelScope.launch {
            if (baseSalary <= 0) {
                _result.value = "Por favor ingrese un salario válido"
                return@launch
            }
            val result = (baseSalary / 240) * 1.25
            val formattedResult = formatCurrency(result)
            addToHistory(
                "Hora Extra Diurna",
                formattedResult,
                mapOf("Salario Base" to formatCurrency(baseSalary))
            )
            _result.value = formattedResult
        }
    }

    fun calculateNighttimeOvertime(baseSalary: Double) {
        viewModelScope.launch {
            if (baseSalary <= 0) {
                _result.value = "Por favor ingrese un salario válido"
                return@launch
            }
            val result = (baseSalary / 240) * 1.75
            val formattedResult = formatCurrency(result)
            addToHistory(
                "Hora Extra Nocturna",
                formattedResult,
                mapOf("Salario Base" to formatCurrency(baseSalary))
            )
            _result.value = formattedResult
        }
    }

    fun calculateHolidayOvertime(baseSalary: Double) {
        viewModelScope.launch {
            if (baseSalary <= 0) {
                _result.value = "Por favor ingrese un salario válido"
                return@launch
            }
            val result = (baseSalary / 240) * 2
            val formattedResult = formatCurrency(result)
            addToHistory(
                "Hora Extra Dominical/Festiva",
                formattedResult,
                mapOf("Salario Base" to formatCurrency(baseSalary))
            )
            _result.value = formattedResult
        }
    }

    private fun addToHistory(title: String, result: String, inputs: Map<String, String>) {
        historyViewModel.addToHistory(CalculationResult(title, result, inputs))
    }
}