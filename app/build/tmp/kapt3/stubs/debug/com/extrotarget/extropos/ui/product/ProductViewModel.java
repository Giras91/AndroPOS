package com.extrotarget.extropos.ui.product;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\rJ\u000e\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u0012J\u000e\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\rJ\u0010\u0010&\u001a\u00020\u001f2\b\u0010\'\u001a\u0004\u0018\u00010\rJ\u0018\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\f0\u000bJ\u0010\u0010(\u001a\u0004\u0018\u00010\u00122\u0006\u0010 \u001a\u00020\rJ\u0006\u0010)\u001a\u00020\u001fJ\b\u0010*\u001a\u00020\u001fH\u0002J\u000e\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\rJ\u000e\u0010-\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u0012R&\u0010\t\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R)\u0010\u0015\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\f0\u000b0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018\u00a8\u0006."}, d2 = {"Lcom/extrotarget/extropos/ui/product/ProductViewModel;", "Landroidx/lifecycle/ViewModel;", "productRepository", "Lcom/extrotarget/extropos/domain/repository/IProductRepository;", "addCategoryUseCase", "Lcom/extrotarget/extropos/domain/usecase/AddCategoryUseCase;", "getCategoriesUseCase", "Lcom/extrotarget/extropos/domain/usecase/GetCategoriesUseCase;", "(Lcom/extrotarget/extropos/domain/repository/IProductRepository;Lcom/extrotarget/extropos/domain/usecase/AddCategoryUseCase;Lcom/extrotarget/extropos/domain/usecase/GetCategoriesUseCase;)V", "_categories", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lkotlin/Pair;", "", "_error", "_isLoading", "", "_products", "Lcom/extrotarget/extropos/domain/model/Product;", "allProducts", "", "categories", "Lkotlinx/coroutines/flow/StateFlow;", "getCategories", "()Lkotlinx/coroutines/flow/StateFlow;", "error", "getError", "isLoading", "products", "getProducts", "addCategory", "", "id", "name", "addProduct", "product", "deleteProduct", "productId", "filterByCategory", "categoryId", "getProductById", "loadProducts", "loadProductsFromDatabase", "searchProducts", "query", "updateProduct", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ProductViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.repository.IProductRepository productRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.AddCategoryUseCase addCategoryUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.GetCategoriesUseCase getCategoriesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.extrotarget.extropos.domain.model.Product>> _products = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.Product>> products = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.extrotarget.extropos.domain.model.Product> allProducts;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<kotlin.Pair<java.lang.String, java.lang.String>>> _categories = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<kotlin.Pair<java.lang.String, java.lang.String>>> categories = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    
    @javax.inject.Inject()
    public ProductViewModel(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.repository.IProductRepository productRepository, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.AddCategoryUseCase addCategoryUseCase, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.GetCategoriesUseCase getCategoriesUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.Product>> getProducts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<kotlin.Pair<java.lang.String, java.lang.String>>> getCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getError() {
        return null;
    }
    
    public final void loadProducts() {
    }
    
    private final void loadProductsFromDatabase() {
    }
    
    public final void searchProducts(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void filterByCategory(@org.jetbrains.annotations.Nullable()
    java.lang.String categoryId) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.extrotarget.extropos.domain.model.Product getProductById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    public final void addCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void addProduct(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.Product product) {
    }
    
    public final void updateProduct(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.Product product) {
    }
    
    public final void deleteProduct(@org.jetbrains.annotations.NotNull()
    java.lang.String productId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> getCategories() {
        return null;
    }
}