package com.example.trackingapp.domain.model

import com.google.firebase.database.IgnoreExtraProperties
import java.util.Date


@IgnoreExtraProperties
data class Expense(
    var id : String? = "",
    var userId : String? = "",
    var title : String? = "",
    var description : String? = "",
    var amount : Double? = 0.0,
    val date : Date? = Date(),
    val category: ExpenseCategory? = ExpenseCategory.OTHER
)