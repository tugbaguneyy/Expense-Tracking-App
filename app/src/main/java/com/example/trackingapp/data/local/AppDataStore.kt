package com.example.trackingapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore(private var context: Context) {

    private val Context.ds : DataStore<Preferences> by preferencesDataStore(name = "settings")

    companion object {
        val ONBOARDING_KEY = booleanPreferencesKey("onboarding")
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val LANGUAGE = stringPreferencesKey("language")
    }

    fun isOnboarding(): Flow<Boolean> {
        return context.ds.data.map {
            it[ONBOARDING_KEY] ?: false
        }
    }

    suspend fun setOnboarding(isOnboarding: Boolean) {
        context.ds.edit {
            it[ONBOARDING_KEY] = isOnboarding
        }
    }

    fun isDarkMode(): Flow<Boolean> {
        return context.ds.data.map {
            it[DARK_MODE] ?: false
        }
    }

    suspend fun setDarkMode(isDarkMode : Boolean) {
        context.ds.edit {
            it[DARK_MODE] = isDarkMode
        }
    }

    fun isLanguage(): Flow<String> {
        return context.ds.data.map {
            it[LANGUAGE] ?: "en"
        }
    }

    suspend fun setLanguage(isLanguage : String) {
        context.ds.edit {
            it[LANGUAGE] = isLanguage
        }
    }
}