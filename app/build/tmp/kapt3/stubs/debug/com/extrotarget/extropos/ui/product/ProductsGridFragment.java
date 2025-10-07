package com.extrotarget.extropos.ui.product;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001c\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001bJ\u0018\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b0 0\u001fJ\u0010\u0010!\u001a\u0004\u0018\u00010\u00182\u0006\u0010\"\u001a\u00020\u001bJ\u000e\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020%J\b\u0010&\u001a\u00020\u0016H\u0002J\b\u0010\'\u001a\u00020\u0016H\u0002J\u0010\u0010(\u001a\u00020\u00162\u0006\u0010)\u001a\u00020*H\u0016J$\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u0001002\b\u00101\u001a\u0004\u0018\u000102H\u0016J\b\u00103\u001a\u00020\u0016H\u0016J\u001a\u00104\u001a\u00020\u00162\u0006\u00105\u001a\u00020,2\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u000e\u00106\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u00107\u001a\u00020\u0016H\u0002J\b\u00108\u001a\u00020\u0016H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00069"}, d2 = {"Lcom/extrotarget/extropos/ui/product/ProductsGridFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/extrotarget/extropos/databinding/FragmentProductsGridBinding;", "binding", "getBinding", "()Lcom/extrotarget/extropos/databinding/FragmentProductsGridBinding;", "cartViewModel", "Lcom/extrotarget/extropos/ui/cart/CartViewModel;", "getCartViewModel", "()Lcom/extrotarget/extropos/ui/cart/CartViewModel;", "cartViewModel$delegate", "Lkotlin/Lazy;", "productViewModel", "Lcom/extrotarget/extropos/ui/product/ProductViewModel;", "getProductViewModel", "()Lcom/extrotarget/extropos/ui/product/ProductViewModel;", "productViewModel$delegate", "productsAdapter", "Lcom/extrotarget/extropos/ui/product/ProductsAdapter;", "addProductToCart", "", "product", "Lcom/extrotarget/extropos/domain/model/Product;", "debugRunSearch", "query", "", "filterByCategory", "categoryId", "getAvailableCategories", "", "Lkotlin/Pair;", "getProductById", "id", "hideInternalSearch", "hide", "", "loadProducts", "observeViewModel", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setSearchQuery", "setupRecyclerView", "setupSearch", "app_debug"})
public final class ProductsGridFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.extrotarget.extropos.databinding.FragmentProductsGridBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy productViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy cartViewModel$delegate = null;
    private com.extrotarget.extropos.ui.product.ProductsAdapter productsAdapter;
    
    public ProductsGridFragment() {
        super();
    }
    
    private final com.extrotarget.extropos.databinding.FragmentProductsGridBinding getBinding() {
        return null;
    }
    
    private final com.extrotarget.extropos.ui.product.ProductViewModel getProductViewModel() {
        return null;
    }
    
    private final com.extrotarget.extropos.ui.cart.CartViewModel getCartViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
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
    
    public final void setSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void filterByCategory(@org.jetbrains.annotations.Nullable()
    java.lang.String categoryId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> getAvailableCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.extrotarget.extropos.domain.model.Product getProductById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    public final void hideInternalSearch(boolean hide) {
    }
    
    public final void debugRunSearch(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupSearch() {
    }
    
    private final void observeViewModel() {
    }
    
    private final void loadProducts() {
    }
    
    private final void addProductToCart(com.extrotarget.extropos.domain.model.Product product) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}