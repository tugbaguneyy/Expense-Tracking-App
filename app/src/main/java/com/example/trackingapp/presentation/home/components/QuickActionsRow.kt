package com.example.trackingapp.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Category
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun QuickActionsRow(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        QuickActionButton(
            icon = Icons.Outlined.Analytics,
            text = "Raporlar",
            onClick = {  }
        )
        QuickActionButton(
            icon = Icons.Outlined.Category,
            text = "Kategoriler",
            onClick = {  }
        )
    }
}