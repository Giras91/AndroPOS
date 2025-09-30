package com.extrotarget.extropos.controls;

/**
 * Control - Base class for control components
 * Adapted from multiPOS Control.kt for AndroPOS
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/extrotarget/extropos/controls/Control;", "", "()V", "action", "", "jar", "Lcom/extrotarget/extropos/util/Jar;", "controlAction", "Companion", "app_debug"})
public abstract class Control {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.String, kotlin.jvm.functions.Function0<com.extrotarget.extropos.controls.Control>> controls = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.extrotarget.extropos.controls.Control.Companion Companion = null;
    
    public Control() {
        super();
    }
    
    public void controlAction(@org.jetbrains.annotations.Nullable()
    com.extrotarget.extropos.util.Jar jar) {
    }
    
    public void action(@org.jetbrains.annotations.Nullable()
    com.extrotarget.extropos.util.Jar jar) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void register(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<? extends com.extrotarget.extropos.controls.Control> factory) {
    }
    
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.Nullable()
    public static final com.extrotarget.extropos.controls.Control factory(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u0005H\u0007J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/extrotarget/extropos/controls/Control$Companion;", "", "()V", "controls", "", "", "Lkotlin/Function0;", "Lcom/extrotarget/extropos/controls/Control;", "factory", "name", "register", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @kotlin.jvm.JvmStatic()
        public final void register(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<? extends com.extrotarget.extropos.controls.Control> factory) {
        }
        
        @kotlin.jvm.JvmStatic()
        @org.jetbrains.annotations.Nullable()
        public final com.extrotarget.extropos.controls.Control factory(@org.jetbrains.annotations.NotNull()
        java.lang.String name) {
            return null;
        }
    }
}