package com.example.trackingapp.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackingapp.domain.model.Expense
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val db : FirebaseDatabase,
    private val auth : FirebaseAuth
) : ViewModel(){

    fun addExpense(
        title : String,
        description : String,
        amount : Double
    ){
        viewModelScope.launch {
            val userId=auth.currentUser?.uid ?: return@launch
            val ref = db.reference.child("expenses").push()
            val id = ref.key ?: return@launch
            val expense= Expense(
                id=id,
                userId=userId,
                title=title,
                description=description,
                amount=amount
            )
            ref.setValue(expense)
        }
    }
}