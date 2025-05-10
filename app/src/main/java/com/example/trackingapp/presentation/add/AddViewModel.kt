package com.example.trackingapp.presentation.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackingapp.domain.model.Expense
import com.example.trackingapp.domain.model.ExpenseCategory
import com.example.trackingapp.domain.usecase.CurrentUserUseCase
import com.example.trackingapp.util.Constants.REFS_EXPENSES
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val db : FirebaseDatabase,
    private val currentUserUseCase: CurrentUserUseCase
) : ViewModel(){

    fun addExpense(
        title : String,
        description : String,
        amount : Double,
        category: ExpenseCategory,
        date: Date
    ){
        viewModelScope.launch {
            val userId = currentUserUseCase().first()?.uid ?: return@launch
            val ref = db.reference.child(REFS_EXPENSES).push()
            val id = ref.key ?: return@launch
            val expense= Expense(
                id=id,
                userId=userId,
                title=title,
                description=description,
                amount=amount,
                category = category,
                date = date
            )
            ref.setValue(expense)
        }
    }
}