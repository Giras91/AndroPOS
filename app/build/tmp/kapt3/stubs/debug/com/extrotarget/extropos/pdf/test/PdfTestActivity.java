package com.extrotarget.extropos.pdf.test;

/**
 * Test activity to demonstrate PDF generation features.
 * This shows how to use the PdfGenerationService to generate receipts, reports, and invoices.
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\nH\u0002J\b\u0010\u000f\u001a\u00020\nH\u0002J\b\u0010\u0010\u001a\u00020\nH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0011"}, d2 = {"Lcom/extrotarget/extropos/pdf/test/PdfTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "pdfService", "Lcom/extrotarget/extropos/pdf/PdfGenerationService;", "getPdfService", "()Lcom/extrotarget/extropos/pdf/PdfGenerationService;", "setPdfService", "(Lcom/extrotarget/extropos/pdf/PdfGenerationService;)V", "listAllPdfs", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "testGenerateDailySalesReport", "testGenerateInvoice", "testGenerateReceipt", "app_debug"})
public final class PdfTestActivity extends androidx.appcompat.app.AppCompatActivity {
    @javax.inject.Inject()
    public com.extrotarget.extropos.pdf.PdfGenerationService pdfService;
    
    public PdfTestActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.pdf.PdfGenerationService getPdfService() {
        return null;
    }
    
    public final void setPdfService(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.pdf.PdfGenerationService p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void testGenerateReceipt() {
    }
    
    private final void testGenerateDailySalesReport() {
    }
    
    private final void testGenerateInvoice() {
    }
    
    private final void listAllPdfs() {
    }
}