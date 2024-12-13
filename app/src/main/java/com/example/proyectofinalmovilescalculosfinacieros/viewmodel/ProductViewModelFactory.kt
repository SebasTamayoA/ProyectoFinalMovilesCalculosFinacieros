package com.example.proyectofinalmovilescalculosfinacieros.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductViewModelFactory(private val historyViewModel: HistoryViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(historyViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}