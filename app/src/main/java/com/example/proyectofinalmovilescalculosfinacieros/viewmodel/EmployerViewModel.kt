package com.example.proyectofinalmovilescalculosfinacieros.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalmovilescalculosfinacieros.model.CalculationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class EmployerViewModel(private val historyViewModel: HistoryViewModel) : ViewModel() {
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

    fun calculateParafiscalContributions(baseSalary: Double) {
        viewModelScope.launch {
            if (baseSalary <= 0) {
                _result.value = "Por favor ingrese un salario válido"
                return@launch
            }
            val result = baseSalary * 0.09 // 9% del salario base
            val formattedResult = formatCurrency(result)
            addToHistory(
                "Aportes Parafiscales",
                formattedResult,
                mapOf("Salario Base" to formatCurrency(baseSalary))
            )
            _result.value = formattedResult
        }
    }

    fun calculateSocialSecurity(baseSalary: Double) {
        viewModelScope.launch {
            if (baseSalary <= 0) {
                _result.value = "Por favor ingrese un salario válido"
                return@launch
            }
            val result = baseSalary * 0.205 // 20.5% del salario base
            val formattedResult = formatCurrency(result)
            addToHistory(
                "Seguridad Social",
                formattedResult,
                mapOf("Salario Base" to formatCurrency(baseSalary))
            )
            _result.value = formattedResult
        }
    }

    fun calculateBenefits(baseSalary: Double) {
        viewModelScope.launch {
            if (baseSalary <= 0) {
                _result.value = "Por favor ingrese un salario válido"
                return@launch
            }
            val result = baseSalary * 0.2183 // 21.83% del salario base
            val formattedResult = formatCurrency(result)
            addToHistory(
                "Prestaciones Sociales",
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