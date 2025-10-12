package com.extrotarget.extropos.printer.domain.service

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.extrotarget.extropos.printer.domain.model.PrinterConfig
import com.extrotarget.extropos.printer.domain.model.ConnectionType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

private val Context.printerConfigDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "printer_config"
)

/**
 * Service for managing printer configurations
 */
@Singleton
class PrinterConfigService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    
    private val dataStore = context.printerConfigDataStore
    private val json = Json { ignoreUnknownKeys = true }
    
    companion object {
        private val PRINTER_CONFIGS_KEY = stringPreferencesKey("printer_configs")
        private val DEFAULT_PRINTER_ID_KEY = stringPreferencesKey("default_printer_id")
    }

    /**
     * Get all saved printer configurations
     */
    fun getAllConfigs(): Flow<List<PrinterConfig>> = dataStore.data.map { preferences ->
        val configsJson = preferences[PRINTER_CONFIGS_KEY] ?: "[]"
        try {
            json.decodeFromString<List<PrinterConfigDto>>(configsJson).map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Save a printer configuration
     */
    suspend fun saveConfig(config: PrinterConfig) {
        dataStore.edit { preferences ->
            val currentConfigsJson = preferences[PRINTER_CONFIGS_KEY] ?: "[]"
            val currentConfigs = try {
                json.decodeFromString<List<PrinterConfigDto>>(currentConfigsJson)
            } catch (e: Exception) {
                emptyList()
            }
            
            val updatedConfigs = currentConfigs.filter { it.id != config.id } + 
                listOf(PrinterConfigDto.fromDomain(config))
            
            preferences[PRINTER_CONFIGS_KEY] = json.encodeToString(updatedConfigs)
            
            // Set as default if it's the first printer or explicitly marked as default
            if (config.isDefault || currentConfigs.isEmpty()) {
                preferences[DEFAULT_PRINTER_ID_KEY] = config.id
            }
        }
    }

    /**
     * Delete a printer configuration
     */
    suspend fun deleteConfig(configId: String) {
        dataStore.edit { preferences ->
            val currentConfigsJson = preferences[PRINTER_CONFIGS_KEY] ?: "[]"
            val currentConfigs = try {
                json.decodeFromString<List<PrinterConfigDto>>(currentConfigsJson)
            } catch (e: Exception) {
                emptyList()
            }
            
            val updatedConfigs = currentConfigs.filter { it.id != configId }
            preferences[PRINTER_CONFIGS_KEY] = json.encodeToString(updatedConfigs)
            
            // Clear default if this was the default printer
            if (preferences[DEFAULT_PRINTER_ID_KEY] == configId) {
                preferences.remove(DEFAULT_PRINTER_ID_KEY)
            }
        }
    }

    /**
     * Set default printer
     */
    suspend fun setDefaultPrinter(configId: String) {
        val configs = getAllConfigs().first().toMutableList()
        
        // Set all configs to non-default
        configs.forEachIndexed { index, config ->
            configs[index] = config.copy(isDefault = false)
        }
        
        // Set the selected config as default
        val targetIndex = configs.indexOfFirst { it.id == configId }
        if (targetIndex >= 0) {
            configs[targetIndex] = configs[targetIndex].copy(isDefault = true)
        }
        
        // Save updated configs
        val configsJson = Json.encodeToString(configs)
        context.printerConfigDataStore.edit { preferences ->
            preferences[PRINTER_CONFIGS_KEY] = configsJson
        }
    }

    suspend fun getDefaultPrinter(): PrinterConfig? {
        return getAllConfigs().first().find { it.isDefault }
    }

    /**
     * Get the default printer configuration
     */
    fun getDefaultConfig(): Flow<PrinterConfig?> = dataStore.data.map { preferences ->
        val defaultId = preferences[DEFAULT_PRINTER_ID_KEY]
        if (defaultId != null) {
            val configsJson = preferences[PRINTER_CONFIGS_KEY] ?: "[]"
            try {
                json.decodeFromString<List<PrinterConfigDto>>(configsJson)
                    .firstOrNull { it.id == defaultId }
                    ?.toDomain()
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }

    /**
     * Get configuration by ID
     */
    suspend fun getConfigById(configId: String): PrinterConfig? {
        val configs = getAllConfigs().first()
        return configs.firstOrNull { it.id == configId }
    }

    /**
     * Test printer connection
     */
    suspend fun testConnection(config: PrinterConfig): Boolean {
        // TODO: Implement actual connection testing
        // For now, simulate a test
        return try {
            delay(1000) // Simulate connection test delay
            Random.nextBoolean() // Random success/failure for demo
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateTestResult(configId: String, success: Boolean) {
        val configs = getAllConfigs().first().toMutableList()
        val index = configs.indexOfFirst { it.id == configId }
        
        if (index >= 0) {
            configs[index] = configs[index].copy(lastTestResult = success)
            
            // Save updated configs
            val configsJson = Json.encodeToString(configs)
            context.printerConfigDataStore.edit { preferences ->
                preferences[PRINTER_CONFIGS_KEY] = configsJson
            }
        }
    }
}

// Data transfer object for JSON serialization
@kotlinx.serialization.Serializable
private data class PrinterConfigDto(
    val id: String,
    val name: String,
    val connectionType: String,
    val address: String,
    val port: Int? = null,
    val selectedSdk: String,
    val isDefault: Boolean = false,
    val lastTestResult: Boolean? = null
) {
    fun toDomain(): PrinterConfig = PrinterConfig(
        id = id,
        name = name,
        connectionType = ConnectionType.valueOf(connectionType),
        address = address,
        port = port,
        selectedSdk = selectedSdk,
        isDefault = isDefault,
        lastTestResult = lastTestResult
    )
    
    companion object {
        fun fromDomain(config: PrinterConfig): PrinterConfigDto = PrinterConfigDto(
            id = config.id,
            name = config.name,
            connectionType = config.connectionType.name,
            address = config.address,
            port = config.port,
            selectedSdk = config.selectedSdk,
            isDefault = config.isDefault,
            lastTestResult = config.lastTestResult
        )
    }
}