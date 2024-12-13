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
import com.example.proyectofinalmovilescalculosfinacieros.viewmodel.EmployeeViewModel

@Composable
fun EmployeeScreen(
    viewModel: EmployeeViewModel = viewModel(),
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
                text = "Cálculos de Empleado",
                style = MaterialTheme.typography.headlineMedium
            )

            // Entrada para el salario base
            NumberInputField(
                label = "Salario Base",
                value = inputBaseSalary,
                onValueChange = { inputBaseSalary = it }
            )

            // Botón para calcular salario neto
            Button(
                onClick = {
                    val baseSalary = inputBaseSalary.toDoubleOrNull() ?: 0.0
                    viewModel.calculateNetSalary(baseSalary)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Salario Neto")
            }

            // Botón para calcular hora extra diurna
            Button(
                onClick = {
                    val baseSalary = inputBaseSalary.toDoubleOrNull() ?: 0.0
                    viewModel.calculateDaytimeOvertime(baseSalary)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Hora Extra Diurna")
            }

            // Botón para calcular hora extra nocturna
            Button(
                onClick = {
                    val baseSalary = inputBaseSalary.toDoubleOrNull() ?: 0.0
                    viewModel.calculateNighttimeOvertime(baseSalary)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Hora Extra Nocturna")
            }

            // Botón para calcular hora dominical/festiva
            Button(
                onClick = {
                    val baseSalary = inputBaseSalary.toDoubleOrNull() ?: 0.0
                    viewModel.calculateHolidayOvertime(baseSalary)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Hora Dominical/Festiva")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Mostrar el resultado
            Text(
                text = "Resultado: $result",
                style = MaterialTheme.typography.bodyLarge
            )

            // Botón para navegar al historial.
            Button(
                onClick = onNavigateToHistory,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver Historial")
            }

            Button(
                onClick = onNavigateBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Volver")
            }
        }
    }
}