package com.extrotarget.extropos.views;

/**
 * PosDisplays - Manager for multiple display components
 * Adapted from multiPOS PosDisplays.kt for AndroPOS
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002\u00a8\u0006\n"}, d2 = {"Lcom/extrotarget/extropos/views/PosDisplays;", "Lcom/extrotarget/extropos/views/PosLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "home", "", "Companion", "app_debug"})
public final class PosDisplays extends com.extrotarget.extropos.views.PosLayout {
    @org.jetbrains.annotations.Nullable()
    private static java.lang.String first;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.extrotarget.extropos.views.PosDisplay> displays = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.extrotarget.extropos.views.PosDisplays.Companion Companion = null;
    
    public PosDisplays(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null, null);
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void add(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.views.PosDisplay posDisplay) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void remove(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.views.PosDisplay posDisplay) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void update() {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void clear() {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void message(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void message(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.util.Jar message) {
    }
    
    private final void home() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H\u0007J\b\u0010\u0011\u001a\u00020\u000fH\u0007J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\tH\u0007J\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H\u0007J\b\u0010\u0015\u001a\u00020\u000fH\u0007R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u00a8\u0006\u0016"}, d2 = {"Lcom/extrotarget/extropos/views/PosDisplays$Companion;", "", "()V", "displays", "", "Lcom/extrotarget/extropos/views/PosDisplay;", "getDisplays", "()Ljava/util/List;", "first", "", "getFirst", "()Ljava/lang/String;", "setFirst", "(Ljava/lang/String;)V", "add", "", "posDisplay", "clear", "message", "Lcom/extrotarget/extropos/util/Jar;", "remove", "update", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getFirst() {
            return null;
        }
        
        public final void setFirst(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.extrotarget.extropos.views.PosDisplay> getDisplays() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic()
        public final void add(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.views.PosDisplay posDisplay) {
        }
        
        @kotlin.jvm.JvmStatic()
        public final void remove(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.views.PosDisplay posDisplay) {
        }
        
        @kotlin.jvm.JvmStatic()
        public final void update() {
        }
        
        @kotlin.jvm.JvmStatic()
        public final void clear() {
        }
        
        @kotlin.jvm.JvmStatic()
        public final void message(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
        }
        
        @kotlin.jvm.JvmStatic()
        public final void message(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.util.Jar message) {
        }
    }
}