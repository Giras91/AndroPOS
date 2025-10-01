package com.extrotarget.extropos.views;

/**
 * PosDisplay - Interface for display components
 * Adapted from multiPOS PosDisplay.kt for AndroPOS
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016\u00a8\u0006\n"}, d2 = {"Lcom/extrotarget/extropos/views/PosDisplay;", "", "clear", "", "del", "enter", "message", "Lcom/extrotarget/extropos/util/Jar;", "", "update", "app_debug"})
public abstract interface PosDisplay {
    
    public abstract void update();
    
    public abstract void clear();
    
    public abstract void message(@org.jetbrains.annotations.NotNull()
    java.lang.String message);
    
    public abstract void message(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.util.Jar message);
    
    public abstract void del();
    
    public abstract void enter();
    
    /**
     * PosDisplay - Interface for display components
     * Adapted from multiPOS PosDisplay.kt for AndroPOS
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
        
        public static void update(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.views.PosDisplay $this) {
        }
        
        public static void clear(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.views.PosDisplay $this) {
        }
        
        public static void message(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.views.PosDisplay $this, @org.jetbrains.annotations.NotNull()
        java.lang.String message) {
        }
        
        public static void message(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.views.PosDisplay $this, @org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.util.Jar message) {
        }
        
        public static void del(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.views.PosDisplay $this) {
        }
        
        public static void enter(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.views.PosDisplay $this) {
        }
    }
}