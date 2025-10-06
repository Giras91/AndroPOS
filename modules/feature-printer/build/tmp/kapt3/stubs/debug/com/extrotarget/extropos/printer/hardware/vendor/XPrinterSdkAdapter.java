package com.extrotarget.extropos.printer.hardware.vendor;

import com.extrotarget.extropos.printer.hardware.PrinterWrapper;
import java.lang.reflect.Method;

/**
 * Adapter that tries to use the official XPrinter SDK if present. If the SDK classes
 * are not available at runtime, it falls back to the GenericAdapter (raw TCP).
 *
 * The adapter uses reflection so the SDK AAR can be added later without compile-time
 * dependency. To enable the SDK capabilities, add the XPrinter AAR or Maven artifact
 * to module dependencies (see README).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0011\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0013J\u000e\u0010\u0014\u001a\u00020\u0015H\u0096@\u00a2\u0006\u0002\u0010\u0013J?\u0010\u0016\u001a\u0004\u0018\u00010\b2\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u001a2\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0018\u0018\u00010\u001aH\u0002\u00a2\u0006\u0002\u0010\u001cJ\b\u0010\u001d\u001a\u00020\u0012H\u0002J\u0016\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 H\u0096@\u00a2\u0006\u0002\u0010!R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/extrotarget/extropos/printer/hardware/vendor/XPrinterSdkAdapter;", "Lcom/extrotarget/extropos/printer/hardware/PrinterWrapper;", "host", "", "port", "", "(Ljava/lang/String;I)V", "connectMethod", "Ljava/lang/reflect/Method;", "delegate", "disconnectMethod", "id", "getId", "()Ljava/lang/String;", "sdkInstance", "", "writeMethod", "connect", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disconnect", "", "findMethod", "clazz", "Ljava/lang/Class;", "names", "", "paramTypesCandidates", "(Ljava/lang/Class;[Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", "tryInitSdk", "write", "data", "", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "feature-printer_debug"})
public final class XPrinterSdkAdapter implements com.extrotarget.extropos.printer.hardware.PrinterWrapper {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String host = null;
    private final int port = 0;
    @org.jetbrains.annotations.Nullable()
    private com.extrotarget.extropos.printer.hardware.PrinterWrapper delegate;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Object sdkInstance;
    @org.jetbrains.annotations.Nullable()
    private java.lang.reflect.Method connectMethod;
    @org.jetbrains.annotations.Nullable()
    private java.lang.reflect.Method writeMethod;
    @org.jetbrains.annotations.Nullable()
    private java.lang.reflect.Method disconnectMethod;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    
    public XPrinterSdkAdapter(@org.jetbrains.annotations.NotNull()
    java.lang.String host, int port) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getId() {
        return null;
    }
    
    private final boolean tryInitSdk() {
        return false;
    }
    
    private final java.lang.reflect.Method findMethod(java.lang.Class<?> clazz, java.lang.String[] names, java.lang.Class<?>[] paramTypesCandidates) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object connect(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object disconnect(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object write(@org.jetbrains.annotations.NotNull()
    byte[] data, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}