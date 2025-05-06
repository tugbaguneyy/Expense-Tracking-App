package com.example.trackingapp.domain.model

enum class ExpenseCategory(val displayName: String) {
    FOOD(displayName = "Yemek"),
    TRANSPORT(displayName = "Ulaşım"),
    SHOPPING(displayName = "Alışveriş"),
    BILLS(displayName = "Faturalar"),
    ENTERTAINMENT(displayName = "Eğlence"),
    HEALTH(displayName = "Sağlık"),
    OTHER(displayName = "Diğer");
}