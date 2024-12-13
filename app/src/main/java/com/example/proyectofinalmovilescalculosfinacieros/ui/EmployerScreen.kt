package com.example.proyectofinalmovilescalculosfinacieros.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinalmovilescalculosfinacieros.R
import com.example.proyectofinalmovilescalculosfinacieros.ui.components.NumberInputField
import com.example.proyectofinalmovilescalculosfinacieros.viewmodel.EmployerViewModel

@Composable
fun EmployerScreen(
    viewModel: EmployerViewModel = viewModel(),
    onNavigateToHistory: () -> Unit,
    onNavigateBack: () -> Unit
) {
    // Variables de entrada
    var inputBaseSalary by remember { mutableStateOf("") }
    val result by viewModel.result.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Cálculos de Empleador",
                style = MaterialTheme.typography.headlineMedium
            )

            // Entrada para el salario base
            NumberInputField(
                label = "Salario Base",
                value = inputBaseSalary,
                onValueChange = { inputBaseSalary = it }
            )

            // Botón para calcular aportes parafiscales
            Button(
                onClick = {
                    val baseSalary = inputBaseSalary.toDoubleOrNull() ?: 0.0
                    viewModel.calculateParafiscalContributions(baseSalary)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Aportes Parafiscales")
            }

            // Botón para calcular seguridad social
            Button(
                onClick = {
                    val baseSalary = inputBaseSalary.toDoubleOrNull() ?: 0.0
                    viewModel.calculateSocialSecurity(baseSalary)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Seguridad Social")
            }

            // Botón para calcular prestaciones sociales
            Button(
                onClick = {
                    val baseSalary = inputBaseSalary.toDoubleOrNull() ?: 0.0
                    viewModel.calculateBenefits(baseSalary)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Prestaciones Sociales")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Mostrar el resultado
            Text(
                text = "Resultado: $result",
                style = MaterialTheme.typography.bodyLarge
            )

            // Botón para ver historial
            Button(
                onClick = onNavigateToHistory,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver Historial")
            }

            // Botón para volver
            Button(
                onClick = onNavigateBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver")
            }
        }
    }
}