package com.example.trackingapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackingapp.domain.model.Expense
import com.example.trackingapp.domain.usecase.CurrentUserUseCase
import com.example.trackingapp.domain.usecase.SignOutUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val auth : FirebaseAuth,
    private val db : FirebaseDatabase,
    private val signOutUseCase: SignOutUseCase,
    private val currentUserUseCase: CurrentUserUseCase
) : ViewModel() {

    private val userId=auth.currentUser?.uid ?: ""
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean>
        get() = _isAuthenticated.asStateFlow()

    private val _allExpense = MutableStateFlow<List<Expense>>(emptyList())
    val allExpense: StateFlow<List<Expense>>
        get() = _allExpense.asStateFlow()

    init {
        isUserAuthenticated()
        getAllExpense()
    }


    fun signOut(){
        viewModelScope.launch {
            signOutUseCase()
            _isAuthenticated.value = false
        }
    }

    private fun getAllExpense(){
        db.getReference("expenses").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val expenses=mutableListOf<Expense>()

                for (data in snapshot.children) {
                    val expense = data.getValue(Expense::class.java)
                    if (expense != null && expense.userId == userId) {
                        expenses.add(expense)
                    }
                }
                _allExpense.value=expenses
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Veri çekme iptal edildi: ${error.message}")
            }
        })
    }

    private fun isUserAuthenticated(){
        val isActive = auth.currentUser != null
        _isAuthenticated.value = isActive
    }

    fun deleteExpense(expenseId: String) {
        viewModelScope.launch {
            db.getReference("expenses").child(expenseId).removeValue()
        }

    }
}