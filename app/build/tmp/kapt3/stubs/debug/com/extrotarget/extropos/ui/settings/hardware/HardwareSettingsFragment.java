package com.extrotarget.extropos.ui.settings.hardware;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015H\u0002J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J$\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010\'\u001a\u00020\u001dH\u0016J\u001a\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020 2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010*\u001a\u00020\u001dH\u0002J\b\u0010+\u001a\u00020\u001dH\u0002J\b\u0010,\u001a\u00020\u001dH\u0002J\b\u0010-\u001a\u00020\u001dH\u0002J\b\u0010.\u001a\u00020\u001dH\u0002J\u0010\u0010/\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011\u00a8\u00060"}, d2 = {"Lcom/extrotarget/extropos/ui/settings/hardware/HardwareSettingsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/extrotarget/extropos/databinding/FragmentHardwareSettingsBinding;", "binding", "getBinding", "()Lcom/extrotarget/extropos/databinding/FragmentHardwareSettingsBinding;", "printerService", "Lcom/extrotarget/extropos/printer/service/GlobalPrinterService;", "getPrinterService", "()Lcom/extrotarget/extropos/printer/service/GlobalPrinterService;", "setPrinterService", "(Lcom/extrotarget/extropos/printer/service/GlobalPrinterService;)V", "viewModel", "Lcom/extrotarget/extropos/ui/settings/hardware/HardwareSettingsViewModel;", "getViewModel", "()Lcom/extrotarget/extropos/ui/settings/hardware/HardwareSettingsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "generateSampleReceiptData", "", "", "", "generateSampleReceiptText", "settings", "Lcom/extrotarget/extropos/data/model/ReceiptSettings;", "getCurrentSettingsFromUI", "loadCurrentSettings", "", "observeViewModel", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "printSampleReceipt", "saveCurrentSettings", "setupReceiptCustomization", "setupUI", "showReceiptPreview", "updateUIFromSettings", "app_debug"})
public final class HardwareSettingsFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.extrotarget.extropos.databinding.FragmentHardwareSettingsBinding _binding;
    @javax.inject.Inject()
    public com.extrotarget.extropos.printer.service.GlobalPrinterService printerService;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    
    public HardwareSettingsFragment() {
        super();
    }
    
    private final com.extrotarget.extropos.databinding.FragmentHardwareSettingsBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.printer.service.GlobalPrinterService getPrinterService() {
        return null;
    }
    
    public final void setPrinterService(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.printer.service.GlobalPrinterService p0) {
    }
    
    private final com.extrotarget.extropos.ui.settings.hardware.HardwareSettingsViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupUI() {
    }
    
    private final void setupReceiptCustomization() {
    }
    
    private final void loadCurrentSettings() {
    }
    
    private final void observeViewModel() {
    }
    
    private final void updateUIFromSettings(com.extrotarget.extropos.data.model.ReceiptSettings settings) {
    }
    
    private final com.extrotarget.extropos.data.model.ReceiptSettings getCurrentSettingsFromUI() {
        return null;
    }
    
    private final void saveCurrentSettings() {
    }
    
    private final void showReceiptPreview() {
    }
    
    private final void printSampleReceipt() {
    }
    
    private final java.lang.String generateSampleReceiptText(com.extrotarget.extropos.data.model.ReceiptSettings settings) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> generateSampleReceiptData() {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}