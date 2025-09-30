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
import android.content.res.Resources
import android.widget.LinearLayout
import android.graphics.Color
import android.view.View
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity

/**
 * PosLayout - Custom layout class for POS UI components
 * Adapted from multiPOS PosLayout.java for AndroPOS
 */
open class PosLayout(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    protected var context: Context = context
    protected var attrs: AttributeSet? = attrs
    protected var resources: Resources = context.resources

    init {
        layoutParams = LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
    }

    fun getIntAttr(name: String, defValue: Int): Int {
        attrs?.let { attributes ->
            for (i in 0 until attributes.attributeCount) {
                if (attributes.getAttributeName(i) == name) {
                    return attributes.getAttributeIntValue(i, defValue)
                }
            }
        }
        return defValue
    }

    fun getStringAttr(name: String, defValue: String): String {
        attrs?.let { attributes ->
            for (i in 0 until attributes.attributeCount) {
                if (attributes.getAttributeName(i) == name) {
                    return attributes.getAttributeValue(i) ?: defValue
                }
            }
        }
        return defValue
    }

    fun getBooleanAttr(name: String, defValue: Boolean): Boolean {
        attrs?.let { attributes ->
            for (i in 0 until attributes.attributeCount) {
                if (attributes.getAttributeName(i) == name) {
                    return attributes.getAttributeBooleanValue(i, defValue)
                }
            }
        }
        return defValue
    }

    fun getFloatAttr(name: String, defValue: Float): Float {
        attrs?.let { attributes ->
            for (i in 0 until attributes.attributeCount) {
                if (attributes.getAttributeName(i) == name) {
                    return attributes.getAttributeFloatValue(i, defValue)
                }
            }
        }
        return defValue
    }

    fun getDimensionAttr(name: String, defValue: Float): Float {
        attrs?.let { attributes ->
            for (i in 0 until attributes.attributeCount) {
                if (attributes.getAttributeName(i) == name) {
                    return attributes.getAttributeFloatValue(i, defValue)
                }
            }
        }
        return defValue
    }

    /**
     * Smooth transition between views
     */
    fun transitionTo(view: View, duration: Long = 300) {
        val scene = Scene(this, view)
        val transition = Fade().apply {
            this.duration = duration
        }
        TransitionManager.go(scene, transition)
    }

    /**
     * Slide transition
     */
    fun slideIn(view: View, fromEdge: Int = Gravity.END, duration: Long = 300) {
        val scene = Scene(this, view)
        val transition = Slide(fromEdge).apply {
            this.duration = duration
        }
        TransitionManager.go(scene, transition)
    }

    /**
     * Get color from theme or resources
     */
    fun getThemeColor(attr: Int): Int {
        val typedArray = context.theme.obtainStyledAttributes(intArrayOf(attr))
        val color = typedArray.getColor(0, Color.BLACK)
        typedArray.recycle()
        return color
    }

    /**
     * Get dimension from theme or resources
     */
    fun getThemeDimension(attr: Int): Float {
        val typedArray = context.theme.obtainStyledAttributes(intArrayOf(attr))
        val dimension = typedArray.getDimension(0, 0f)
        typedArray.recycle()
        return dimension
    }
}