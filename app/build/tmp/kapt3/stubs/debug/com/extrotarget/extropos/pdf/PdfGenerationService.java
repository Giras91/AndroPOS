package com.extrotarget.extropos.pdf;

/**
 * Service for generating PDF documents (receipts, reports, invoices).
 * Follows AndroPOS conventions: uses cents for amounts, MYR currency.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J \u0010\f\u001a\u0004\u0018\u00010\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011J.\u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000e2\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\tJ2\u0010\u0018\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000e2\b\b\u0002\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\tJ\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u000eJ\u0010\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/extrotarget/extropos/pdf/PdfGenerationService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "dateFormat", "Ljava/text/SimpleDateFormat;", "dateOnlyFormat", "formatCentsToRM", "", "cents", "", "generateDailySalesReport", "sales", "", "Lcom/extrotarget/extropos/data/local/entity/SaleEntity;", "date", "Ljava/util/Date;", "generateInvoice", "sale", "items", "Lcom/extrotarget/extropos/domain/model/CartItem;", "customerName", "customerAddress", "generateReceipt", "storeName", "storeAddress", "getAllPdfFiles", "Ljava/io/File;", "getOutputFile", "fileName", "app_debug"})
public final class PdfGenerationService {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat dateFormat = null;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat dateOnlyFormat = null;
    
    @javax.inject.Inject()
    public PdfGenerationService(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Generate a receipt PDF for a sale transaction.
     *
     * @param sale The sale entity with transaction details
     * @param items List of cart items in the sale
     * @param storeName Name of the store (e.g. "ExtroPOS")
     * @param storeAddress Store address line
     * @return File path to the generated PDF, or null if failed
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String generateReceipt(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.SaleEntity sale, @org.jetbrains.annotations.NotNull()
    java.util.List<com.extrotarget.extropos.domain.model.CartItem> items, @org.jetbrains.annotations.NotNull()
    java.lang.String storeName, @org.jetbrains.annotations.NotNull()
    java.lang.String storeAddress) {
        return null;
    }
    
    /**
     * Generate a daily sales report PDF.
     *
     * @param sales List of sales for the day
     * @param date Date for the report
     * @return File path to the generated PDF, or null if failed
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String generateDailySalesReport(@org.jetbrains.annotations.NotNull()
    java.util.List<com.extrotarget.extropos.data.local.entity.SaleEntity> sales, @org.jetbrains.annotations.NotNull()
    java.util.Date date) {
        return null;
    }
    
    /**
     * Generate an invoice PDF (for B2B transactions).
     *
     * @param sale The sale entity
     * @param items List of cart items
     * @param customerName Customer/business name
     * @param customerAddress Customer address
     * @return File path to the generated PDF, or null if failed
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String generateInvoice(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.SaleEntity sale, @org.jetbrains.annotations.NotNull()
    java.util.List<com.extrotarget.extropos.domain.model.CartItem> items, @org.jetbrains.annotations.NotNull()
    java.lang.String customerName, @org.jetbrains.annotations.NotNull()
    java.lang.String customerAddress) {
        return null;
    }
    
    /**
     * Format cents (Long) to RM currency string.
     * Example: 12345 cents → "RM 123.45"
     */
    private final java.lang.String formatCentsToRM(long cents) {
        return null;
    }
    
    /**
     * Get output file in the app's documents directory.
     */
    private final java.io.File getOutputFile(java.lang.String fileName) {
        return null;
    }
    
    /**
     * Get all generated PDF files.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.io.File> getAllPdfFiles() {
        return null;
    }
}