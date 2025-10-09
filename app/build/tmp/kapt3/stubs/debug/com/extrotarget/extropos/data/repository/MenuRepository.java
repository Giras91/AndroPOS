package com.extrotarget.extropos.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0096@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\nH\u0096@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\n2\u0006\u0010\u0015\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\n2\u0006\u0010\u0017\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u001eJ\f\u0010\u001f\u001a\u00020\u000b*\u00020 H\u0002J\f\u0010\u001f\u001a\u00020\u000e*\u00020!H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/extrotarget/extropos/data/repository/MenuRepository;", "Lcom/extrotarget/extropos/domain/repository/IMenuRepository;", "categoryDao", "Lcom/extrotarget/extropos/data/local/dao/CategoryDao;", "menuItemDao", "Lcom/extrotarget/extropos/data/local/dao/MenuItemDao;", "moshi", "Lcom/squareup/moshi/Moshi;", "(Lcom/extrotarget/extropos/data/local/dao/CategoryDao;Lcom/extrotarget/extropos/data/local/dao/MenuItemDao;Lcom/squareup/moshi/Moshi;)V", "getAllCategories", "", "Lcom/extrotarget/extropos/domain/model/Category;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllMenuItems", "Lcom/extrotarget/extropos/domain/model/MenuItem;", "getCategoryById", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMenuItemById", "getMenuItemsByCategory", "categoryId", "searchMenuItems", "query", "upsertCategory", "", "category", "(Lcom/extrotarget/extropos/domain/model/Category;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertMenuItem", "item", "(Lcom/extrotarget/extropos/domain/model/MenuItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomain", "Lcom/extrotarget/extropos/data/local/entity/CategoryEntity;", "Lcom/extrotarget/extropos/data/local/entity/MenuItemEntity;", "app_debug"})
public final class MenuRepository implements com.extrotarget.extropos.domain.repository.IMenuRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.dao.CategoryDao categoryDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.dao.MenuItemDao menuItemDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.squareup.moshi.Moshi moshi = null;
    
    @javax.inject.Inject()
    public MenuRepository(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.dao.CategoryDao categoryDao, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.dao.MenuItemDao menuItemDao, @org.jetbrains.annotations.NotNull()
    com.squareup.moshi.Moshi moshi) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAllCategories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.Category>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object upsertCategory(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.Category category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object upsertMenuItem(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.MenuItem item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getCategoryById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.Category> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getMenuItemsByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String categoryId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.MenuItem>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getMenuItemById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.MenuItem> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAllMenuItems(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.MenuItem>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object searchMenuItems(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.MenuItem>> $completion) {
        return null;
    }
    
    private final com.extrotarget.extropos.domain.model.Category toDomain(com.extrotarget.extropos.data.local.entity.CategoryEntity $this$toDomain) {
        return null;
    }
    
    private final com.extrotarget.extropos.domain.model.MenuItem toDomain(com.extrotarget.extropos.data.local.entity.MenuItemEntity $this$toDomain) {
        return null;
    }
}