package com.extrotarget.extropos.ui.guide

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.extrotarget.extropos.R
import com.extrotarget.extropos.ui.main.DashboardPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages contextual hints and overlays for Guide Mode.
 * Shows helpful tooltips and guidance when Guide Mode is enabled.
 */
@Singleton
class GuideOverlayManager @Inject constructor(
    private val prefs: DashboardPrefs
) : DefaultLifecycleObserver {
    
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var isGuideEnabled = false
    private val activeOverlays = mutableMapOf<String, View>()
    private var currentActivity: Activity? = null

    init {
        scope.launch {
            prefs.guideMode.collectLatest { enabled ->
                isGuideEnabled = enabled
                if (!enabled) {
                    clearAllOverlays()
                }
            }
        }
    }

    fun attachToActivity(activity: Activity) {
        currentActivity = activity
        if (activity is LifecycleOwner) {
            activity.lifecycle.addObserver(this)
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        clearAllOverlays()
        currentActivity = null
    }

    /**
     * Show a contextual hint for a specific view element.
     */
    fun showHint(
        targetView: View,
        hintId: String,
        message: String,
        position: HintPosition = HintPosition.BOTTOM
    ) {
        if (!isGuideEnabled || currentActivity == null) return
        
        // Don't show duplicate hints
        if (activeOverlays.containsKey(hintId)) return

        val activity = currentActivity ?: return
        val rootView = activity.findViewById<ViewGroup>(android.R.id.content)
        
        val hintView = createHintView(activity, message, position)
        positionHintView(hintView, targetView, rootView, position)
        
        rootView.addView(hintView)
        activeOverlays[hintId] = hintView
        
        // Auto-dismiss after 8 seconds
        hintView.postDelayed({
            dismissHint(hintId)
        }, 8000)
    }

    /**
     * Dismiss a specific hint by ID.
     */
    fun dismissHint(hintId: String) {
        activeOverlays[hintId]?.let { view ->
            (view.parent as? ViewGroup)?.removeView(view)
            activeOverlays.remove(hintId)
        }
    }

    /**
     * Clear all active overlays.
     */
    fun clearAllOverlays() {
        activeOverlays.values.forEach { view ->
            (view.parent as? ViewGroup)?.removeView(view)
        }
        activeOverlays.clear()
    }

    private fun createHintView(context: Context, message: String, position: HintPosition): TextView {
        return TextView(context).apply {
            text = message
            textSize = 14f
            setTextColor(Color.WHITE)
            setPadding(32, 24, 32, 24)
            gravity = Gravity.CENTER
            maxWidth = 800
            
            // Create rounded background
            val background = GradientDrawable().apply {
                setColor(Color.parseColor("#DD000000"))
                cornerRadius = 16f
            }
            setBackground(background)
            
            // Add pointer based on position
            when (position) {
                HintPosition.TOP -> compoundDrawablePadding = 8
                HintPosition.BOTTOM -> compoundDrawablePadding = 8
                HintPosition.LEFT -> compoundDrawablePadding = 8
                HintPosition.RIGHT -> compoundDrawablePadding = 8
            }
            
            elevation = 8f
        }
    }

    private fun positionHintView(
        hintView: TextView,
        targetView: View,
        rootView: ViewGroup,
        position: HintPosition
    ) {
        val targetLocation = IntArray(2)
        targetView.getLocationInWindow(targetLocation)
        
        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        
        val margin = 32
        when (position) {
            HintPosition.BOTTOM -> {
                layoutParams.topMargin = targetLocation[1] + targetView.height + margin
                layoutParams.leftMargin = targetLocation[0] - (hintView.maxWidth / 4)
            }
            HintPosition.TOP -> {
                layoutParams.topMargin = targetLocation[1] - 120 - margin
                layoutParams.leftMargin = targetLocation[0] - (hintView.maxWidth / 4)
            }
            HintPosition.LEFT -> {
                layoutParams.topMargin = targetLocation[1] - 40
                layoutParams.leftMargin = targetLocation[0] - 400 - margin
            }
            HintPosition.RIGHT -> {
                layoutParams.topMargin = targetLocation[1] - 40
                layoutParams.leftMargin = targetLocation[0] + targetView.width + margin
            }
        }
        
        hintView.layoutParams = layoutParams
    }

    enum class HintPosition {
        TOP, BOTTOM, LEFT, RIGHT
    }
}