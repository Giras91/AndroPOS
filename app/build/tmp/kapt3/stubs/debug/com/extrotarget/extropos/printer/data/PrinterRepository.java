package com.extrotarget.extropos.printer.data;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0004H\u0002J$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000f\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0010"}, d2 = {"Lcom/extrotarget/extropos/printer/data/PrinterRepository;", "Lcom/extrotarget/extropos/printer/data/IPrinterRepository;", "()V", "availablePrinters", "", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "preferredWrappers", "Lcom/extrotarget/extropos/printer/hardware/PrinterWrapper;", "print", "Lkotlin/Result;", "", "job", "Lcom/extrotarget/extropos/printer/domain/model/PrintJob;", "print-gIAlu-s", "(Lcom/extrotarget/extropos/printer/domain/model/PrintJob;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class PrinterRepository implements com.extrotarget.extropos.printer.data.IPrinterRepository {
    
    @javax.inject.Inject()
    public PrinterRepository() {
        super();
    }
    
    private final java.util.List<com.extrotarget.extropos.printer.hardware.PrinterWrapper> preferredWrappers() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object availablePrinters(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion) {
        return null;
    }
}