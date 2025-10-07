package com.extrotarget.extropos.printer.data;

/**
 * High-level repository for printing operations. Implementations should
 * coordinate between available printer SDK wrappers and fallbacks.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J$\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\r"}, d2 = {"Lcom/extrotarget/extropos/printer/data/IPrinterRepository;", "", "availablePrinters", "", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "print", "Lkotlin/Result;", "", "job", "Lcom/extrotarget/extropos/printer/domain/model/PrintJob;", "print-gIAlu-s", "(Lcom/extrotarget/extropos/printer/domain/model/PrintJob;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface IPrinterRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object availablePrinters(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
}