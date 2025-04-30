package com.example.trackingapp.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Auth : Screen

    @Serializable
    data object Home : Screen

    @Serializable
    data object DetailSettings : Screen

    @Serializable
    data object Tracking : Screen

    @Serializable
    data object Add : Screen
}