package com.extrotarget.extropos.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a6@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bH\u00a6@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\b2\u0006\u0010\u0010\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\b2\u0006\u0010\u0012\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\tH\u00a6@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\fH\u00a6@\u00a2\u0006\u0002\u0010\u0018\u00a8\u0006\u0019"}, d2 = {"Lcom/extrotarget/extropos/domain/repository/IMenuRepository;", "", "deleteCategoryById", "", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllCategories", "", "Lcom/extrotarget/extropos/domain/model/Category;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllMenuItems", "Lcom/extrotarget/extropos/domain/model/MenuItem;", "getCategoryById", "getMenuItemById", "getMenuItemsByCategory", "categoryId", "searchMenuItems", "query", "upsertCategory", "category", "(Lcom/extrotarget/extropos/domain/model/Category;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertMenuItem", "item", "(Lcom/extrotarget/extropos/domain/model/MenuItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface IMenuRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllCategories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.Category>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategoryById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.Category> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMenuItemsByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String categoryId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.MenuItem>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMenuItemById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.MenuItem> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllMenuItems(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.MenuItem>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchMenuItems(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.MenuItem>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertCategory(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.Category category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertMenuItem(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.MenuItem item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteCategoryById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}