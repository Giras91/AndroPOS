package com.extrotarget.extropos.data.local.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * LocalAuthManager securely stores and retrieves the user's local PIN for offline auth.
 * Responsibilities:
 *  - Create/Retrieve encrypted preferences
 *  - Save hashed PIN (never store raw)
 *  - Validate a raw PIN against stored hash
 *  - Track whether technician one-time PIN was consumed
 */
@Singleton
class LocalAuthManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    companion object {
        private const val PREF_NAME = "local_auth.secure"
        private const val KEY_USER_PIN_HASH = "user_pin_hash"
        private const val KEY_TECH_PIN_USED = "tech_pin_used"
        private const val TECH_PIN = "888888" // one-time technician PIN
    }

    private val masterKey by lazy {
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    private val prefs: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            context,
            PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun hasUserPin(): Boolean = prefs.contains(KEY_USER_PIN_HASH)

    fun saveUserPin(rawPin: String) {
        require(rawPin.length in 4..8) { "PIN must be 4-8 digits" }
        prefs.edit().putString(KEY_USER_PIN_HASH, hashPin(rawPin)).apply()
    }

    fun validateUserPin(rawPin: String): Boolean {
        val stored = prefs.getString(KEY_USER_PIN_HASH, null) ?: return false
        return stored == hashPin(rawPin)
    }

    fun canUseTechnicianPin(rawPin: String): Boolean {
        if (prefs.getBoolean(KEY_TECH_PIN_USED, false)) return false
        return rawPin == TECH_PIN
    }

    fun consumeTechnicianPin() {
        prefs.edit().putBoolean(KEY_TECH_PIN_USED, true).apply()
    }

    private fun hashPin(raw: String): String {
        // Lightweight hash (not cryptographic) just to avoid plain storage; for stronger security integrate Argon2/Bcrypt.
        // Since this stays on-device and is already encrypted at rest, a fast hash is acceptable.
        return raw.reversed() + "#" + raw.sumOf { it.code }.toString(16)
    }
}
