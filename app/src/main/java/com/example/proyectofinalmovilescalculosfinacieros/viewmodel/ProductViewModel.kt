package com.example.proyectofinalmovilescalculosfinacieros.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalmovilescalculosfinacieros.model.CalculationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class ProductViewModel(private val historyViewModel: HistoryViewModel) : ViewModel() {
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

    fun calculatePriceWithIVA(priceBase: Double) {
        viewModelScope.launch {
            if (priceBase <= 0) {
                _result.value = "Por favor ingrese un precio válido"
                return@launch
            }
            val result = priceBase * 1.19
            val formattedResult = formatCurrency(result)
            addToHistory(
                "Precio con IVA",
                formattedResult,
                mapOf("Precio Base" to formatCurrency(priceBase))
            )
            _result.value = formattedResult
        }
    }

    fun calculateMargin(price: Double, cost: Double) {
        viewModelScope.launch {
            if (price <= 0 || cost < 0 || cost >= price) {
                _result.value = "Datos no válidos para margen"
                return@launch
            }
            val result = ((price - cost) / price) * 100
            val formattedResult = "${formatNumber(result)}%"
            addToHistory(
                "Margen de Ganancia",
                formattedResult,
                mapOf("Precio" to formatCurrency(price), "Costo" to formatCurrency(cost))
            )
            _result.value = formattedResult
        }
    }

    fun calculateBreakEven(fixedCosts: Double, price: Double, variableCost: Double) {
        viewModelScope.launch {
            if (fixedCosts <= 0 || price <= variableCost) {
                _result.value = "Datos no válidos para punto de equilibrio"
                return@launch
            }
            val result = fixedCosts / (price - variableCost)
            val formattedResult = formatNumber(result)
            addToHistory(
                "Punto de Equilibrio",
                formattedResult,
                mapOf(
                    "Costos Fijos" to formatCurrency(fixedCosts),
                    "Precio" to formatCurrency(price),
                    "Costo Variable" to formatCurrency(variableCost)
                )
            )
            _result.value = formattedResult
        }
    }

    fun calculateROI(investment: Double, earnings: Double) {
        viewModelScope.launch {
            if (investment <= 0) {
                _result.value = "Inversión no válida"
                return@launch
            }
            val result = ((earnings - investment) / investment) * 100
            val formattedResult = "${formatNumber(result)}%"
            addToHistory(
                "Retorno de Inversión",
                formattedResult,
                mapOf("Inversión" to formatCurrency(investment), "Ingresos" to formatCurrency(earnings))
            )
            _result.value = formattedResult
        }
    }

    private fun addToHistory(title: String, result: String, inputs: Map<String, String>) {
        historyViewModel.addToHistory(CalculationResult(title, result, inputs))
    }
}