package com.extrotarget.extropos.ui.main;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0018\u0010\u0017\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f\u00a8\u0006\u001b"}, d2 = {"Lcom/extrotarget/extropos/ui/main/DashboardPrefs;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "dataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "guideMode", "Lkotlinx/coroutines/flow/Flow;", "", "getGuideMode", "()Lkotlinx/coroutines/flow/Flow;", "trainingMode", "getTrainingMode", "userRole", "", "getUserRole", "setGuideModeEnabled", "", "enabled", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setTrainingModeEnabled", "setUserRole", "role", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class DashboardPrefs {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> dataStore = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TRAINING_MODE_KEY = "training_mode_enabled";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String GUIDE_MODE_KEY = "guide_mode_enabled";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String USER_ROLE_KEY = "user_role";
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> trainingMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> guideMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> userRole = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.extrotarget.extropos.ui.main.DashboardPrefs.Companion Companion = null;
    
    @javax.inject.Inject()
    public DashboardPrefs(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getTrainingMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getGuideMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getUserRole() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setTrainingModeEnabled(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setGuideModeEnabled(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setUserRole(@org.jetbrains.annotations.Nullable()
    java.lang.String role, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/extrotarget/extropos/ui/main/DashboardPrefs$Companion;", "", "()V", "GUIDE_MODE_KEY", "", "TRAINING_MODE_KEY", "USER_ROLE_KEY", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}