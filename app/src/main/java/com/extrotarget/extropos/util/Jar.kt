/**
 * Copyright (C) 2023 multiPOS, LLC - Adapted for AndroPOS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of this file except in compliance with the License.
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

/**
 * Jar - JSON-like data structure for AndroPOS
 * Adapted from multiPOS Jar.java for our Clean Architecture needs
 */

package com.extrotarget.extropos.util

import org.json.JSONObject
import org.json.JSONArray
import org.json.JSONException
import java.util.ArrayList
import java.util.HashMap

class Jar {

    private val data: HashMap<String, Any> = HashMap()

    constructor()

    constructor(json: String) {
        parse(json)
    }

    constructor(json: JSONObject) {
        parse(json)
    }

    constructor(jar: Jar) {
        data.putAll(jar.data)
    }

    fun parse(jsonString: String): Jar {
        try {
            val json = JSONObject(jsonString)
            parse(json)
        } catch (e: JSONException) {
            // Handle parsing error gracefully
            put("parse_error", e.message ?: "Unknown error")
        }
        return this
    }

    fun parse(json: JSONObject): Jar {
        val keys = json.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            val value = json.get(key)
            when (value) {
                is JSONObject -> put(key, Jar(value))
                is JSONArray -> {
                    val list = ArrayList<Jar>()
                    for (i in 0 until value.length()) {
                        val item = value.getJSONObject(i)
                        list.add(Jar(item))
                    }
                    put(key, list)
                }
                else -> put(key, value)
            }
        }
        return this
    }

    override fun toString(): String {
        return stringify()
    }

    fun getList(key: String): ArrayList<Jar> {
        val value = get(key)
        return if (value is ArrayList<*>) {
            @Suppress("UNCHECKED_CAST")
            value as ArrayList<Jar>
        } else {
            ArrayList()
        }
    }

    fun has(key: String): Boolean {
        return data.containsKey(key)
    }

    fun getObj(key: String): Any? {
        return data[key]
    }
    
    fun get(key: String): Jar {
        val value = data[key]
        return if (value is Jar) {
            value
        } else {
            Jar() // Return empty Jar if not found or not a Jar
        }
    }

    fun getInt(key: String): Int {
        val value = data[key]
        return when (value) {
            is Number -> value.toInt()
            is String -> value.toIntOrNull() ?: 0
            else -> 0
        }
    }

    fun getLong(key: String): Long {
        val value = data[key]
        return when (value) {
            is Number -> value.toLong()
            is String -> value.toLongOrNull() ?: 0L
            else -> 0L
        }
    }

    fun getDouble(key: String): Double {
        val value = data[key]
        return when (value) {
            is Number -> value.toDouble()
            is String -> value.toDoubleOrNull() ?: 0.0
            else -> 0.0
        }
    }

    fun getString(key: String): String {
        val value = data[key]
        return when (value) {
            null -> ""
            is String -> value
            else -> value.toString()
        }
    }

    fun getBoolean(key: String): Boolean {
        val value = data[key]
        return when (value) {
            is Boolean -> value
            is String -> value.toBoolean()
            is Number -> value.toInt() != 0
            else -> false
        }
    }

    fun put(key: String, value: Any): Jar {
        data[key] = value
        return this
    }

    fun put(key: String, value: List<*>): Jar {
        data[key] = value
        return this
    }

    fun remove(key: String): Jar {
        data.remove(key)
        return this
    }

    fun removeAll(): Jar {
        data.clear()
        return this
    }

    fun json(): JSONObject {
        val json = JSONObject()
        for ((key, value) in data) {
            when (value) {
                is Jar -> json.put(key, value.json())
                is List<*> -> {
                    val array = JSONArray()
                    for (item in value) {
                        if (item is Jar) {
                            array.put(item.json())
                        } else {
                            array.put(item)
                        }
                    }
                    json.put(key, array)
                }
                else -> json.put(key, value)
            }
        }
        return json
    }

    fun keys(): Iterator<String> {
        return data.keys.iterator()
    }

    fun copy(jar: Jar): Jar {
        data.clear()
        data.putAll(jar.data)
        return this
    }

    fun stringify(): String {
        return json().toString()
    }

    fun update(): Jar {
        // Placeholder for update operations
        return this
    }

    fun complete(): Jar {
        // Placeholder for completion operations
        return this
    }

    fun fold(): Jar {
        // Placeholder for fold operations
        return this
    }

    fun taxes(): Jar {
        // Placeholder for tax calculations
        return this
    }
}