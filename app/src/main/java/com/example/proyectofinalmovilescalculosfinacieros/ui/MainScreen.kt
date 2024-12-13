package com.example.proyectofinalmovilescalculosfinacieros.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyectofinalmovilescalculosfinacieros.R

@Composable
fun MainScreen(onNavigateToFinancial: () -> Unit, onNavigateToEmployee: () -> Unit, onNavigateToEmployer: () -> Unit) {
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido a nuestra App Financiera",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onNavigateToFinancial,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cálculos de Producto")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onNavigateToEmployee,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cálculos de Empleado")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onNavigateToEmployer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cálculos de Empleador")
            }
        }
    }
}