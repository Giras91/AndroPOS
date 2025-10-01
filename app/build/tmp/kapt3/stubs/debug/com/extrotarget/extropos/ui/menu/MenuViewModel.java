package com.extrotarget.extropos.ui.menu;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020\u001fJ\u0006\u0010!\u001a\u00020\u001fJ\u000e\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u000eJ\u000e\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u000eJ\u000e\u0010&\u001a\u00020\u001f2\u0006\u0010\'\u001a\u00020\fR\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0016R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016\u00a8\u0006("}, d2 = {"Lcom/extrotarget/extropos/ui/menu/MenuViewModel;", "Landroidx/lifecycle/ViewModel;", "getCategories", "Lcom/extrotarget/extropos/domain/usecase/GetCategoriesUseCase;", "getMenuItemsUseCase", "Lcom/extrotarget/extropos/domain/usecase/GetMenuItemsUseCase;", "searchMenuItemsUseCase", "Lcom/extrotarget/extropos/domain/usecase/SearchMenuItemsUseCase;", "(Lcom/extrotarget/extropos/domain/usecase/GetCategoriesUseCase;Lcom/extrotarget/extropos/domain/usecase/GetMenuItemsUseCase;Lcom/extrotarget/extropos/domain/usecase/SearchMenuItemsUseCase;)V", "_categories", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/extrotarget/extropos/domain/model/Category;", "_error", "", "_isLoading", "", "_menuItems", "Lcom/extrotarget/extropos/domain/model/MenuItem;", "_selectedCategory", "categories", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "error", "getError", "isLoading", "menuItems", "getMenuItems", "selectedCategory", "getSelectedCategory", "clearSearch", "", "loadAllMenuItems", "loadCategories", "loadMenuItemsByCategory", "categoryId", "searchMenuItems", "query", "selectCategory", "category", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MenuViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.GetCategoriesUseCase getCategories = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.GetMenuItemsUseCase getMenuItemsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.SearchMenuItemsUseCase searchMenuItemsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.extrotarget.extropos.domain.model.Category>> _categories = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.Category>> categories = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.extrotarget.extropos.domain.model.MenuItem>> _menuItems = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.MenuItem>> menuItems = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.extrotarget.extropos.domain.model.Category> _selectedCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.extrotarget.extropos.domain.model.Category> selectedCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    
    @javax.inject.Inject()
    public MenuViewModel(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.GetCategoriesUseCase getCategories, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.GetMenuItemsUseCase getMenuItemsUseCase, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.SearchMenuItemsUseCase searchMenuItemsUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.Category>> getCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.MenuItem>> getMenuItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.extrotarget.extropos.domain.model.Category> getSelectedCategory() {
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
    
    public final void loadCategories() {
    }
    
    public final void loadAllMenuItems() {
    }
    
    public final void loadMenuItemsByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String categoryId) {
    }
    
    public final void selectCategory(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.Category category) {
    }
    
    public final void searchMenuItems(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void clearSearch() {
    }
}