package com.extrotarget.extropos.ui.menu;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J$\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\u0018H\u0016J\u001a\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010$\u001a\u00020\u0018H\u0002J\b\u0010%\u001a\u00020\u0018H\u0002J\b\u0010&\u001a\u00020\u0018H\u0002J\u0010\u0010\'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020)H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006*"}, d2 = {"Lcom/extrotarget/extropos/ui/menu/MenuFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/extrotarget/extropos/databinding/FragmentMenuBinding;", "binding", "getBinding", "()Lcom/extrotarget/extropos/databinding/FragmentMenuBinding;", "categoryAdapter", "Lcom/extrotarget/extropos/ui/menu/CategoryAdapter;", "menuItemAdapter", "Lcom/extrotarget/extropos/ui/menu/MenuItemAdapter;", "productViewModel", "Lcom/extrotarget/extropos/ui/product/ProductViewModel;", "getProductViewModel", "()Lcom/extrotarget/extropos/ui/product/ProductViewModel;", "productViewModel$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/extrotarget/extropos/ui/menu/MenuViewModel;", "getViewModel", "()Lcom/extrotarget/extropos/ui/menu/MenuViewModel;", "viewModel$delegate", "observeViewModel", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupAddCategory", "setupRecyclerViews", "setupSearch", "showAddToCartDialog", "menuItem", "Lcom/extrotarget/extropos/domain/model/MenuItem;", "app_debug"})
public final class MenuFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.extrotarget.extropos.databinding.FragmentMenuBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy productViewModel$delegate = null;
    private com.extrotarget.extropos.ui.menu.CategoryAdapter categoryAdapter;
    private com.extrotarget.extropos.ui.menu.MenuItemAdapter menuItemAdapter;
    
    public MenuFragment() {
        super();
    }
    
    private final com.extrotarget.extropos.databinding.FragmentMenuBinding getBinding() {
        return null;
    }
    
    private final com.extrotarget.extropos.ui.menu.MenuViewModel getViewModel() {
        return null;
    }
    
    private final com.extrotarget.extropos.ui.product.ProductViewModel getProductViewModel() {
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
    
    private final void setupAddCategory() {
    }
    
    private final void setupRecyclerViews() {
    }
    
    private final void setupSearch() {
    }
    
    private final void observeViewModel() {
    }
    
    private final void showAddToCartDialog(com.extrotarget.extropos.domain.model.MenuItem menuItem) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}