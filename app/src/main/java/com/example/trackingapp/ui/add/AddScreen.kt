package com.example.trackingapp.ui.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun AddScreen(
    navController : NavController
) {
    val viewModel = hiltViewModel<AddViewModel>()

    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val amount = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                navController.navigateUp()
            }
        ) {
            Text(text = "Back")
        }
        Text(text = "Add Screen", fontSize = 24.sp)
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text("Title") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = description.value,
            onValueChange = { description.value = it },
            label = { Text("Description") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = amount.value,
            onValueChange = { amount.value = it },
            label = { Text("Amount") }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.addExpense(
                    title = title.value,
                    description = description.value,
                    amount = amount.value.toDouble()
                )
            }
        ) {
            Text(text = "Add")
        }
    }
}