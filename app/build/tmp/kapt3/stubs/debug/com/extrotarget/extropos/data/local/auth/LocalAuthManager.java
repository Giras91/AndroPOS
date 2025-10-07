package com.extrotarget.extropos.data.local.auth;

/**
 * LocalAuthManager securely stores and retrieves the user's local PIN for offline auth.
 * Responsibilities:
 * - Create/Retrieve encrypted preferences
 * - Save hashed PIN (never store raw)
 * - Validate a raw PIN against stored hash
 * - Track whether technician one-time PIN was consumed
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0011J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0013H\u0002J\u000e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001c"}, d2 = {"Lcom/extrotarget/extropos/data/local/auth/LocalAuthManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "masterKey", "Landroidx/security/crypto/MasterKey;", "getMasterKey", "()Landroidx/security/crypto/MasterKey;", "masterKey$delegate", "Lkotlin/Lazy;", "prefs", "Landroid/content/SharedPreferences;", "getPrefs", "()Landroid/content/SharedPreferences;", "prefs$delegate", "canUseTechnicianPin", "", "rawPin", "", "consumeTechnicianPin", "", "hasUserPin", "hashPin", "raw", "saveUserPin", "validateUserPin", "Companion", "app_debug"})
public final class LocalAuthManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREF_NAME = "local_auth.secure";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_USER_PIN_HASH = "user_pin_hash";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_TECH_PIN_USED = "tech_pin_used";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TECH_PIN = "888888";
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy masterKey$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy prefs$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.extrotarget.extropos.data.local.auth.LocalAuthManager.Companion Companion = null;
    
    @javax.inject.Inject()
    public LocalAuthManager(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final androidx.security.crypto.MasterKey getMasterKey() {
        return null;
    }
    
    private final android.content.SharedPreferences getPrefs() {
        return null;
    }
    
    public final boolean hasUserPin() {
        return false;
    }
    
    public final void saveUserPin(@org.jetbrains.annotations.NotNull()
    java.lang.String rawPin) {
    }
    
    public final boolean validateUserPin(@org.jetbrains.annotations.NotNull()
    java.lang.String rawPin) {
        return false;
    }
    
    public final boolean canUseTechnicianPin(@org.jetbrains.annotations.NotNull()
    java.lang.String rawPin) {
        return false;
    }
    
    public final void consumeTechnicianPin() {
    }
    
    private final java.lang.String hashPin(java.lang.String raw) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/extrotarget/extropos/data/local/auth/LocalAuthManager$Companion;", "", "()V", "KEY_TECH_PIN_USED", "", "KEY_USER_PIN_HASH", "PREF_NAME", "TECH_PIN", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}