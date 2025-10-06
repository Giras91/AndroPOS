package com.extrotarget.extropos.printer.hardware.usb;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import com.extrotarget.extropos.printer.hardware.PrinterWrapper;
import kotlinx.coroutines.Dispatchers;
import java.io.IOException;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\u0012\u001a\u00020\u0013H\u0096@\u00a2\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00020\u0016H\u0096@\u00a2\u0006\u0002\u0010\u0014J\n\u0010\u0017\u001a\u0004\u0018\u00010\u000bH\u0002J\u0016\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0096@\u00a2\u0006\u0002\u0010\u001bR\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/extrotarget/extropos/printer/hardware/usb/USBPrinterAdapter;", "Lcom/extrotarget/extropos/printer/hardware/PrinterWrapper;", "context", "Landroid/content/Context;", "vendorId", "", "productId", "(Landroid/content/Context;II)V", "connection", "Landroid/hardware/usb/UsbDeviceConnection;", "device", "Landroid/hardware/usb/UsbDevice;", "id", "", "getId", "()Ljava/lang/String;", "outEndpoint", "Landroid/hardware/usb/UsbEndpoint;", "connect", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disconnect", "", "findDevice", "write", "data", "", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "feature-printer_debug"})
public final class USBPrinterAdapter implements com.extrotarget.extropos.printer.hardware.PrinterWrapper {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final int vendorId = 0;
    private final int productId = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.Nullable()
    private android.hardware.usb.UsbDeviceConnection connection;
    @org.jetbrains.annotations.Nullable()
    private android.hardware.usb.UsbEndpoint outEndpoint;
    @org.jetbrains.annotations.Nullable()
    private android.hardware.usb.UsbDevice device;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_USB_PERMISSION = "com.extrotarget.extropos.USB_PERMISSION";
    @org.jetbrains.annotations.NotNull()
    public static final com.extrotarget.extropos.printer.hardware.usb.USBPrinterAdapter.Companion Companion = null;
    
    public USBPrinterAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int vendorId, int productId) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getId() {
        return null;
    }
    
    private final android.hardware.usb.UsbDevice findDevice() {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/extrotarget/extropos/printer/hardware/usb/USBPrinterAdapter$Companion;", "", "()V", "ACTION_USB_PERMISSION", "", "feature-printer_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}