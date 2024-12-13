package com.example.proyectofinalmovilescalculosfinacieros.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EmployerViewModelFactory(private val historyViewModel: HistoryViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmployerViewModel(historyViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}