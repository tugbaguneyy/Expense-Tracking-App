package com.example.trackingapp.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Auth : Screen

    @Serializable
    data object Home : Screen

    @Serializable
    data object Settings : Screen

    @Serializable
    data object Tracking : Screen

    @Serializable
    data object Add : Screen

    @Serializable
    data object Register : Screen

    @Serializable
    data object Login : Screen

    @Serializable
    data object List : Screen
}