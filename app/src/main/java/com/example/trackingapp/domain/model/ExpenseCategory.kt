package com.example.trackingapp.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.LocalHospital
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector


enum class ExpenseCategory(val displayName: String, val icon : ImageVector) {
    FOOD(displayName = "Yemek", icon = Icons.Outlined.Restaurant),
    TRANSPORT(displayName = "Ulaşım", icon = Icons.Outlined.DirectionsCar),
    SHOPPING(displayName = "Alışveriş", icon = Icons.Outlined.ShoppingCart ),
    BILLS(displayName = "Faturalar", icon = Icons.Outlined.Receipt),
    ENTERTAINMENT(displayName = "Eğlence", icon = Icons.Outlined.Movie),
    HEALTH(displayName = "Sağlık", icon = Icons.Outlined.LocalHospital),
    OTHER(displayName = "Diğer", icon = Icons.Outlined.Category);
}