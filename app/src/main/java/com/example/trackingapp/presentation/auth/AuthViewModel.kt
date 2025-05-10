package com.example.trackingapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackingapp.domain.usecase.CurrentUserUseCase
import com.example.trackingapp.domain.usecase.SignInWithEmailAndPasswordUseCase
import com.example.trackingapp.domain.usecase.SignUpWithEmailAndPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    private val signUpWithEmailAndPasswordUseCase: SignUpWithEmailAndPasswordUseCase,
    private val currentUserUseCase: CurrentUserUseCase
) : ViewModel() {

    private val _isAuthenticated= MutableStateFlow(false)
    val isAuthenticated : StateFlow<Boolean>
        get() = _isAuthenticated.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage


    init {
        isUserAuthenticated()
    }

    private fun isUserAuthenticated(){
        viewModelScope.launch {
            currentUserUseCase().collect { it ->
                _isAuthenticated.value = it != null
            }
        }
    }

    fun signUp(email: String, password: String,passwordConfirm: String) {
        viewModelScope.launch {
            if(password==passwordConfirm){
                signUpWithEmailAndPasswordUseCase(email, password).collect{
                    _isAuthenticated.value = it
                }
            }
        }
    }

    fun signIn(email : String, password : String){
        viewModelScope.launch {
            signInWithEmailAndPasswordUseCase(email, password).collect{
                _isAuthenticated.value = it
            }
        }
    }
}