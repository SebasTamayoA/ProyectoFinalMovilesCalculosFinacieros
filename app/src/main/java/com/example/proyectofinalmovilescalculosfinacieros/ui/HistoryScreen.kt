package com.example.proyectofinalmovilescalculosfinacieros.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinalmovilescalculosfinacieros.R
import com.example.proyectofinalmovilescalculosfinacieros.model.CalculationResult
import com.example.proyectofinalmovilescalculosfinacieros.viewmodel.HistoryViewModel

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val history by viewModel.history.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Historial de Cálculos", style = MaterialTheme.typography.headlineMedium)

            LazyColumn(modifier = Modifier.weight(1f).padding(8.dp)) {
                items(history) { calculation: CalculationResult ->
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("${calculation.title}: ${calculation.result}")
                        calculation.inputs.forEach { (key, value) ->
                            Text("$key: $value")
                        }
                    }
                }
            }

            Button(onClick = onNavigateBack, modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                Text("Volver")
            }
        }
    }
}