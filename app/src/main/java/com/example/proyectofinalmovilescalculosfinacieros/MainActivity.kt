package com.example.proyectofinalmovilescalculosfinacieros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proyectofinalmovilescalculosfinacieros.ui.navigation.FinancialApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinancialApp()
        }
    }
}