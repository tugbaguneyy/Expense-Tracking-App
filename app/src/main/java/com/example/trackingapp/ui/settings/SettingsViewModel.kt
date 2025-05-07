package com.example.trackingapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackingapp.data.local.AppDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appDataStore: AppDataStore
) : ViewModel() {

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean>
        get() = _isDarkMode.asStateFlow()

    init {
        isDarkMode()
    }

    private fun isDarkMode() {
        viewModelScope.launch {
            appDataStore.isDarkMode().collect {
                _isDarkMode.value = it
            }
        }
    }

    fun setDarkMode(isDarkMode : Boolean){
        viewModelScope.launch {
            appDataStore.setDarkMode(isDarkMode)
        }
    }
}