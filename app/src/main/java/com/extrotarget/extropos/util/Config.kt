/**
 * Copyright (C) 2023 multiPOS, LLC - Adapted for AndroPOS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.extrotarget.extropos.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.view.Display
import android.util.DisplayMetrics
import android.graphics.Point
import android.net.wifi.WifiManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.util.*
import java.net.NetworkInterface
import java.net.InetAddress

/**
 * Config - Configuration management for AndroPOS
 * Adapted from multiPOS Config.java for AndroPOS
 */
class Config(context: Context) {

    private val context: Context = context
    private val resources: Resources = context.resources
    private val data: Jar = Jar()

    init {
        initialize()
    }

    fun initialize() {
        // App version information
        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        data.put("version_name", pInfo.versionName)
            data.put("version_code", pInfo.versionCode)
        } catch (e: PackageManager.NameNotFoundException) {
            data.put("version_name", "unknown")
            data.put("version_code", 0)
        }

        // Device information
        data.put("device_data", deviceData())

        // Network information
        data.put("network_info", networkInfo())

        // Display information
        data.put("display_info", displayInfo())

        // Locale information
        data.put("locale", Locale.getDefault().toString())

        // POS-specific configuration
        initializePosConfig()
    }

    private fun deviceData(): Jar {
        val device = Jar()

        // Basic device info
        device.put("manufacturer", android.os.Build.MANUFACTURER)
        device.put("model", android.os.Build.MODEL)
        device.put("android_version", android.os.Build.VERSION.RELEASE)
        device.put("api_level", android.os.Build.VERSION.SDK_INT)

        // Screen info
        val metrics = resources.displayMetrics
        device.put("screen_width", metrics.widthPixels)
        device.put("screen_height", metrics.heightPixels)
        device.put("screen_density", metrics.density)
        device.put("screen_dpi", metrics.densityDpi)

        return device
    }

    private fun networkInfo(): Jar {
        val network = Jar()

        try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            if (capabilities != null) {
                network.put("wifi", capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                network.put("cellular", capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                network.put("ethernet", capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
            }

            // IP addresses
            val ips = getIPAddresses()
            network.put("ip_addresses", ips as Any)

        } catch (e: Exception) {
            // Jar.put has overloads for Any and List; ensure we pass a suitable type
            val msg = e.message ?: "Unknown network error"
            network.put("error", listOf(msg))
        }

        return network
    }

    private fun displayInfo(): Jar {
        val display = Jar()

        try {
            val displayMetrics = resources.displayMetrics
            val configuration = resources.configuration

            display.put("width", displayMetrics.widthPixels)
            display.put("height", displayMetrics.heightPixels)
            display.put("density", displayMetrics.density)
            display.put("density_dpi", displayMetrics.densityDpi)
            display.put("scaled_density", displayMetrics.scaledDensity)
            display.put("orientation", configuration.orientation)

        } catch (e: Exception) {
            val msg = e.message ?: "Unknown display error"
            display.put("error", listOf(msg))
        }

        return display
    }

    private fun getIPAddresses(): ArrayList<String> {
        val addresses = ArrayList<String>()

        try {
            val interfaces = NetworkInterface.getNetworkInterfaces()
            for (networkInterface in interfaces) {
                val inetAddresses = networkInterface.inetAddresses
                for (inetAddress in inetAddresses) {
                    if (!inetAddress.isLoopbackAddress && inetAddress is java.net.Inet4Address) {
                        addresses.add(inetAddress.hostAddress ?: "")
                    }
                }
            }
        } catch (e: Exception) {
            // Handle exception
        }

        return addresses
    }

    private fun initializePosConfig() {
        // Default POS configuration
        data.put("pos_name", "AndroPOS Terminal")
        data.put("store_name", "Restaurant POS")
        data.put("currency", "MYR")
        data.put("tax_rate", 0.06) // 6% SST
        data.put("receipt_footer", "Thank you for your business!")

        // Display configuration
        data.put("customer_display_enabled", false)
        data.put("ticket_display_enabled", true)

        // Hardware configuration
        data.put("printer_enabled", false)
        data.put("scanner_enabled", false)
        data.put("cash_drawer_enabled", false)

        // Timing configuration
        data.put("session_timeout", 3600000) // 1 hour in milliseconds
        data.put("auto_logout", 300000) // 5 minutes
    }

    fun has(key: String): Boolean {
        return data.has(key)
    }

    fun getString(key: String): String {
        return data.getString(key)
    }

    fun getDouble(key: String): Double {
        return data.getDouble(key)
    }

    fun getBoolean(key: String): Boolean {
        return data.getBoolean(key)
    }

    fun getInt(key: String): Int {
        return data.getInt(key)
    }

    fun getLong(key: String): Long {
        return data.getLong(key)
    }

    fun get(key: String): Jar {
        return data.get(key)
    }

    fun getObj(key: String): Any? {
        return data.getObj(key)
    }
    
    fun put(key: String, value: Any): Config {
        data.put(key, value)
        return this
    }
}