package com.extrotarget.extropos.ui.settings.payment;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0013H\u0016J\u001a\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001f\u001a\u00020\u0013H\u0002J\u0012\u0010 \u001a\u00020\u00132\b\u0010!\u001a\u0004\u0018\u00010\"H\u0002J\b\u0010#\u001a\u00020\u0013H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006$"}, d2 = {"Lcom/extrotarget/extropos/ui/settings/payment/PaymentSettingsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/extrotarget/extropos/databinding/FragmentPaymentSettingsBinding;", "binding", "getBinding", "()Lcom/extrotarget/extropos/databinding/FragmentPaymentSettingsBinding;", "paymentMethodsAdapter", "Lcom/extrotarget/extropos/ui/settings/payment/PaymentMethodsAdapter;", "taxRatesAdapter", "Lcom/extrotarget/extropos/ui/settings/payment/TaxRatesAdapter;", "vm", "Lcom/extrotarget/extropos/ui/settings/payment/PaymentSettingsViewModel;", "getVm", "()Lcom/extrotarget/extropos/ui/settings/payment/PaymentSettingsViewModel;", "vm$delegate", "Lkotlin/Lazy;", "observeViewModel", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupUI", "showAddEditPaymentMethodDialog", "edit", "Lcom/extrotarget/extropos/data/model/PaymentMethod;", "showAddTaxRateDialog", "app_debug"})
public final class PaymentSettingsFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.extrotarget.extropos.databinding.FragmentPaymentSettingsBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy vm$delegate = null;
    private com.extrotarget.extropos.ui.settings.payment.PaymentMethodsAdapter paymentMethodsAdapter;
    private com.extrotarget.extropos.ui.settings.payment.TaxRatesAdapter taxRatesAdapter;
    
    public PaymentSettingsFragment() {
        super();
    }
    
    private final com.extrotarget.extropos.databinding.FragmentPaymentSettingsBinding getBinding() {
        return null;
    }
    
    private final com.extrotarget.extropos.ui.settings.payment.PaymentSettingsViewModel getVm() {
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
    
    private final void observeViewModel() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void showAddEditPaymentMethodDialog(com.extrotarget.extropos.data.model.PaymentMethod edit) {
    }
    
    private final void showAddTaxRateDialog() {
    }
}