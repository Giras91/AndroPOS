package com.extrotarget.extropos.ui.settings.reporting;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u001a\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010 \u001a\u00020\u001dH\u0002J\b\u0010!\u001a\u00020\u001dH\u0002J+\u0010\"\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010\t2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001d0%H\u0002\u00a2\u0006\u0002\u0010&R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\n\u00a8\u0006\'"}, d2 = {"Lcom/extrotarget/extropos/ui/settings/reporting/ReportingSettingsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/extrotarget/extropos/databinding/FragmentReportingSettingsBinding;", "binding", "getBinding", "()Lcom/extrotarget/extropos/databinding/FragmentReportingSettingsBinding;", "endTimestamp", "", "Ljava/lang/Long;", "productAdapter", "Lcom/extrotarget/extropos/reporting/ProductSummaryAdapter;", "reportingViewModel", "Lcom/extrotarget/extropos/reporting/ReportingViewModel;", "getReportingViewModel", "()Lcom/extrotarget/extropos/reporting/ReportingViewModel;", "reportingViewModel$delegate", "Lkotlin/Lazy;", "startTimestamp", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "view", "setupActions", "setupUI", "showDatePicker", "initial", "onChosen", "Lkotlin/Function1;", "(Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "app_debug"})
public final class ReportingSettingsFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.extrotarget.extropos.databinding.FragmentReportingSettingsBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy reportingViewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private com.extrotarget.extropos.reporting.ProductSummaryAdapter productAdapter;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long startTimestamp;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long endTimestamp;
    
    public ReportingSettingsFragment() {
        super();
    }
    
    private final com.extrotarget.extropos.databinding.FragmentReportingSettingsBinding getBinding() {
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
    
    private final com.extrotarget.extropos.reporting.ReportingViewModel getReportingViewModel() {
        return null;
    }
    
    private final void setupActions() {
    }
    
    private final void setupUI() {
    }
    
    private final void showDatePicker(java.lang.Long initial, kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onChosen) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}