package com.example.proyectofinalmovilescalculosfinacieros.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EmployeeViewModelFactory(private val historyViewModel: HistoryViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmployeeViewModel(historyViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}