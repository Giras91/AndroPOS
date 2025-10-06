package com.extrotarget.extropos.training;

/**
 * Observes the persisted training mode flag and coordinates sandbox data lifecycle.
 * When training mode is turned OFF, all registered training data sources are cleared.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eJ\u0014\u0010\u000f\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/extrotarget/extropos/training/TrainingModeManager;", "", "prefs", "Lcom/extrotarget/extropos/ui/main/DashboardPrefs;", "(Lcom/extrotarget/extropos/ui/main/DashboardPrefs;)V", "clearables", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function0;", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "training", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isTraining", "", "registerClearable", "onDisable", "app_debug"})
public final class TrainingModeManager {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.concurrent.atomic.AtomicBoolean training = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.concurrent.CopyOnWriteArrayList<kotlin.jvm.functions.Function0<kotlin.Unit>> clearables = null;
    
    @javax.inject.Inject()
    public TrainingModeManager(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.ui.main.DashboardPrefs prefs) {
        super();
    }
    
    public final boolean isTraining() {
        return false;
    }
    
    /**
     * Register a callback invoked when training mode switches OFF.
     */
    public final void registerClearable(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDisable) {
    }
}