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

package com.extrotarget.extropos.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.view.ViewGroup
import com.extrotarget.extropos.util.Jar

/**
 * PosDisplay - Interface for display components
 * Adapted from multiPOS PosDisplay.kt for AndroPOS
 */
interface PosDisplay {
    fun update() {}
    fun clear() {}
    fun message(message: String) {}
    fun message(message: Jar) {}
    fun del() {}
    fun enter() {}
}

/**
 * DefaultDisplay - Default implementation of PosDisplay
 */
open class DefaultDisplay : PosDisplay {
    override fun update() {}
    override fun clear() {}
    override fun message(message: String) {}
    override fun message(message: Jar) {}
}

/**
 * PosDisplays - Manager for multiple display components
 * Adapted from multiPOS PosDisplays.kt for AndroPOS
 */
class PosDisplays(context: Context, attrs: AttributeSet?) : PosLayout(context, attrs) {

    companion object {
        var first: String? = null
        val displays = mutableListOf<PosDisplay>()

        @JvmStatic
        fun add(posDisplay: PosDisplay) {
            displays.add(posDisplay)
        }

        @JvmStatic
        fun remove(posDisplay: PosDisplay) {
            displays.remove(posDisplay)
        }

        @JvmStatic
        fun update() {
            displays.forEach { it.update() }
        }

        @JvmStatic
        fun clear() {
            displays.forEach { it.clear() }
        }

        @JvmStatic
        fun message(message: String) {
            displays.forEach { it.message(message) }
        }

        @JvmStatic
        fun message(message: Jar) {
            displays.forEach { it.message(message) }
        }
    }

    init {
        // Initialize displays based on configuration
        // This would be expanded based on your app's configuration needs
        home()
    }

    private fun home() {
        // Initialize default display state
        clear()
    }
}

/**
 * TicketDisplay - Display for showing current transaction/ticket
 */
class TicketDisplay(context: Context, attrs: AttributeSet?) : PosLayout(context, attrs), PosDisplay {

    override fun update() {
        // Update ticket display with current transaction data
        // Implementation would show current order items, totals, etc.
    }

    override fun clear() {
        // Clear the ticket display
        removeAllViews()
    }

    override fun message(message: String) {
        // Show message on ticket display
        // Could show error messages, status updates, etc.
    }

    override fun message(message: Jar) {
        // Handle structured message data
        message(message.getString("text"))
    }
}

/**
 * CustomerDisplay - Display for customer-facing information
 */
class CustomerDisplay(context: Context, attrs: AttributeSet?) : PosLayout(context, attrs), PosDisplay {

    override fun update() {
        // Update customer display with relevant information
        // Could show total, change due, promotional messages, etc.
    }

    override fun clear() {
        // Clear customer display
        removeAllViews()
    }

    override fun message(message: String) {
        // Show message to customer
    }

    override fun message(message: Jar) {
        // Handle customer-specific messages
    }
}