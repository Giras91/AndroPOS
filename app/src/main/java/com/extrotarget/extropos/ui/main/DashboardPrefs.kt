package com.extrotarget.extropos.ui.main

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val DASHBOARD_PREFS = "dashboard_prefs"

private val Context.dashboardDataStore by preferencesDataStore(name = DASHBOARD_PREFS)

@Singleton
class DashboardPrefs @Inject constructor(
    private val context: Context
) {
    private val dataStore = context.dashboardDataStore

    companion object {
        private const val TRAINING_MODE_KEY = "training_mode_enabled"
        private const val GUIDE_MODE_KEY = "guide_mode_enabled"
        private const val USER_ROLE_KEY = "user_role"
    }

    val trainingMode: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[booleanPreferencesKey(TRAINING_MODE_KEY)] ?: false
        }

    val guideMode: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[booleanPreferencesKey(GUIDE_MODE_KEY)] ?: false
        }

    val userRole: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[stringPreferencesKey(USER_ROLE_KEY)]
        }

    suspend fun setTrainingModeEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(TRAINING_MODE_KEY)] = enabled
        }
    }

    suspend fun setGuideModeEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(GUIDE_MODE_KEY)] = enabled
        }
    }

    suspend fun setUserRole(role: String?) {
        dataStore.edit { preferences ->
            if (role != null) {
                preferences[stringPreferencesKey(USER_ROLE_KEY)] = role
            } else {
                preferences.remove(stringPreferencesKey(USER_ROLE_KEY))
            }
        }
    }
}
