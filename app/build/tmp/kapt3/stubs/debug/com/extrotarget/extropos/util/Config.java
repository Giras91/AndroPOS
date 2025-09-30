package com.extrotarget.extropos.util;

/**
 * Config - Configuration management for AndroPOS
 * Adapted from multiPOS Config.java for AndroPOS
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\u0006H\u0002J\b\u0010\n\u001a\u00020\u0006H\u0002J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u0013H\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00012\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0019\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010\u001e\u001a\u00020\u0006H\u0002J\u0016\u0010\u001f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/extrotarget/extropos/util/Config;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "data", "Lcom/extrotarget/extropos/util/Jar;", "resources", "Landroid/content/res/Resources;", "deviceData", "displayInfo", "get", "key", "", "getBoolean", "", "getDouble", "", "getIPAddresses", "Ljava/util/ArrayList;", "getInt", "", "getLong", "", "getObj", "getString", "has", "initialize", "", "initializePosConfig", "networkInfo", "put", "value", "app_debug"})
public final class Config {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.res.Resources resources = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.util.Jar data = null;
    
    public Config(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void initialize() {
    }
    
    private final com.extrotarget.extropos.util.Jar deviceData() {
        return null;
    }
    
    private final com.extrotarget.extropos.util.Jar networkInfo() {
        return null;
    }
    
    private final com.extrotarget.extropos.util.Jar displayInfo() {
        return null;
    }
    
    private final java.util.ArrayList<java.lang.String> getIPAddresses() {
        return null;
    }
    
    private final void initializePosConfig() {
    }
    
    public final boolean has(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getString(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return null;
    }
    
    public final double getDouble(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return 0.0;
    }
    
    public final boolean getBoolean(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return false;
    }
    
    public final int getInt(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return 0;
    }
    
    public final long getLong(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.util.Jar get(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getObj(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.util.Config put(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.Object value) {
        return null;
    }
}