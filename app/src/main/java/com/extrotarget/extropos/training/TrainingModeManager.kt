package com.extrotarget.extropos.training

import com.extrotarget.extropos.ui.main.DashboardPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Observes the persisted training mode flag and coordinates sandbox data lifecycle.
 * When training mode is turned OFF, all registered training data sources are cleared.
 */
@Singleton
class TrainingModeManager @Inject constructor(
    prefs: DashboardPrefs
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val training = AtomicBoolean(false)
    private val clearables = CopyOnWriteArrayList<() -> Unit>()

    init {
        scope.launch {
            prefs.trainingMode.collectLatest { enabled ->
                val previous = training.getAndSet(enabled)
                if (previous && !enabled) {
                    // Transition from ON -> OFF: purge sandbox
                    clearables.forEach { runCatching { it.invoke() }.getOrElse { } }
                }
            }
        }
    }

    fun isTraining(): Boolean = training.get()

    /** Register a callback invoked when training mode switches OFF. */
    fun registerClearable(onDisable: () -> Unit) { clearables += onDisable }
}
