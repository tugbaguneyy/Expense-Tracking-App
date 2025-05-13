package com.example.trackingapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackingapp.domain.model.Expense
import com.example.trackingapp.domain.usecase.CurrentUserUseCase
import com.example.trackingapp.util.Constants.REFS_EXPENSES
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val db: FirebaseDatabase,
    private val currentUserUseCase: CurrentUserUseCase
) : ViewModel() {

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean>
        get() = _isAuthenticated.asStateFlow()

    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>>
        get() = _expenses.asStateFlow()

    private val _dailyTotal = MutableStateFlow(0.0)
    val dailyTotal: StateFlow<Double>
        get() = _dailyTotal.asStateFlow()

    init {
        isUserAuthenticated()
        getAllExpense()
    }

    private fun isUserAuthenticated() {
        viewModelScope.launch {
            val isActive = currentUserUseCase().first() != null
            _isAuthenticated.value = isActive
        }
    }

    private fun getAllExpense() {
        viewModelScope.launch {
            val userId = currentUserUseCase().first()?.uid ?: return@launch

            db.getReference(REFS_EXPENSES).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val expenses = mutableListOf<Expense>()

                    for (data in snapshot.children) {

                        val expense = data.getValue(Expense::class.java)

                        if (expense != null && expense.userId == userId) {
                            expenses.add(expense)
                        }
                    }
                    _expenses.value = expenses

                    // Veriler yüklendikten SONRA günlük toplamı hesapla
                    calculateDailyTotal(expenses)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Firebase", "Veri çekme iptal edildi: ${error.message}")
                }
            })
        }
    }




    private fun calculateDailyTotal(expenses: List<Expense>) {
        val startOfDay = getStartOfDay()
        val total = expenses
            .filter { it.date!!.after(startOfDay) }
            .sumOf { it.amount!! }

        _dailyTotal.value = total
    }

    private fun getStartOfDay(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        return calendar.time
    }
}