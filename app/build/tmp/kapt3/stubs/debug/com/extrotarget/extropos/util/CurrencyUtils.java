package com.extrotarget.extropos.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\rR\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\n\u0010\u0006\u00a8\u0006\u0013"}, d2 = {"Lcom/extrotarget/extropos/util/CurrencyUtils;", "", "()V", "malaysianFormat", "Ljava/text/NumberFormat;", "getMalaysianFormat", "()Ljava/text/NumberFormat;", "malaysianFormat$delegate", "Lkotlin/Lazy;", "malaysianFormatNoSymbol", "getMalaysianFormatNoSymbol", "malaysianFormatNoSymbol$delegate", "formatAmount", "", "cents", "", "formatAmountNoSymbol", "parseAmount", "amountString", "app_debug"})
public final class CurrencyUtils {
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy malaysianFormat$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy malaysianFormatNoSymbol$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.extrotarget.extropos.util.CurrencyUtils INSTANCE = null;
    
    private CurrencyUtils() {
        super();
    }
    
    private final java.text.NumberFormat getMalaysianFormat() {
        return null;
    }
    
    private final java.text.NumberFormat getMalaysianFormatNoSymbol() {
        return null;
    }
    
    /**
     * Format cents to Malaysian Ringgit currency string
     * @param cents Amount in cents
     * @return Formatted currency string (e.g., "RM 12.50")
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatAmount(long cents) {
        return null;
    }
    
    /**
     * Format cents to Malaysian Ringgit without currency symbol
     * @param cents Amount in cents
     * @return Formatted amount string (e.g., "12.50")
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatAmountNoSymbol(long cents) {
        return null;
    }
    
    /**
     * Parse Malaysian Ringgit string to cents
     * @param amountString Amount string (e.g., "12.50" or "RM 12.50")
     * @return Amount in cents
     */
    public final long parseAmount(@org.jetbrains.annotations.NotNull()
    java.lang.String amountString) {
        return 0L;
    }
}