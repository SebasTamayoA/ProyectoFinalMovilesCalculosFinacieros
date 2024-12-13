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
import com.example.proyectofinalmovilescalculosfinacieros.viewmodel.ProductViewModel
import com.example.proyectofinalmovilescalculosfinacieros.viewmodel.HistoryViewModel
import com.example.proyectofinalmovilescalculosfinacieros.ui.components.NumberInputField
import com.example.proyectofinalmovilescalculosfinacieros.viewmodel.ProductViewModelFactory

@Composable
fun ProductScreen(
    historyViewModel: HistoryViewModel = viewModel(),
    onNavigateToHistory: () -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: com.example.proyectofinalmovilescalculosfinacieros.viewmodel.ProductViewModel
) {
    val productViewModel: ProductViewModel = viewModel(factory = ProductViewModelFactory(historyViewModel))
    var inputPriceBase by remember { mutableStateOf("") }
    var inputCost by remember { mutableStateOf("") }
    var inputFixedCosts by remember { mutableStateOf("") }
    var inputVariableCost by remember { mutableStateOf("") }
    var inputInvestment by remember { mutableStateOf("") }
    var inputEarnings by remember { mutableStateOf("") }
    val result by productViewModel.result.collectAsState()

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
            Text(text = "Cálculos de Producto", style = MaterialTheme.typography.headlineMedium)

            NumberInputField(
                label = "Precio Base",
                value = inputPriceBase,
                onValueChange = { inputPriceBase = it }
            )

            NumberInputField(
                label = "Costo",
                value = inputCost,
                onValueChange = { inputCost = it }
            )

            NumberInputField(
                label = "Costos Fijos",
                value = inputFixedCosts,
                onValueChange = { inputFixedCosts = it }
            )

            NumberInputField(
                label = "Costo Variable",
                value = inputVariableCost,
                onValueChange = { inputVariableCost = it }
            )

            NumberInputField(
                label = "Inversión",
                value = inputInvestment,
                onValueChange = { inputInvestment = it }
            )

            NumberInputField(
                label = "Ingresos",
                value = inputEarnings,
                onValueChange = { inputEarnings = it }
            )

            Button(
                onClick = {
                    val priceBase = inputPriceBase.toDoubleOrNull() ?: 0.0
                    productViewModel.calculatePriceWithIVA(priceBase)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Precio con IVA")
            }

            Button(
                onClick = {
                    val price = inputPriceBase.toDoubleOrNull() ?: 0.0
                    val cost = inputCost.toDoubleOrNull() ?: 0.0
                    productViewModel.calculateMargin(price, cost)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Margen")
            }

            Button(
                onClick = {
                    val fixedCosts = inputFixedCosts.toDoubleOrNull() ?: 0.0
                    val price = inputPriceBase.toDoubleOrNull() ?: 0.0
                    val variableCost = inputVariableCost.toDoubleOrNull() ?: 0.0
                    productViewModel.calculateBreakEven(fixedCosts, price, variableCost)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Punto de Equilibrio")
            }

            Button(
                onClick = {
                    val investment = inputInvestment.toDoubleOrNull() ?: 0.0
                    val earnings = inputEarnings.toDoubleOrNull() ?: 0.0
                    productViewModel.calculateROI(investment, earnings)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular ROI")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Resultado: $result", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onNavigateToHistory,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver Historial")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onNavigateBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver")
            }
        }
    }
}