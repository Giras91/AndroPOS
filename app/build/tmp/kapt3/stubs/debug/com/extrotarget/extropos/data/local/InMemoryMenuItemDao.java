package com.extrotarget.extropos.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0007J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0096@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\f\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\u0012\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u001e\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@\u00a2\u0006\u0002\u0010\u0016J\"\u0010\u0017\u001a\u00020\u00072\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0018\"\u00020\u0005H\u0096@\u00a2\u0006\u0002\u0010\u0019R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/extrotarget/extropos/data/local/InMemoryMenuItemDao;", "Lcom/extrotarget/extropos/data/local/dao/MenuItemDao;", "()V", "items", "", "Lcom/extrotarget/extropos/data/local/entity/MenuItemEntity;", "clearAll", "", "getAllAvailable", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByCategory", "categoryId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "searchByName", "query", "updateAvailability", "isAvailable", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsert", "", "([Lcom/extrotarget/extropos/data/local/entity/MenuItemEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class InMemoryMenuItemDao implements com.extrotarget.extropos.data.local.dao.MenuItemDao {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.extrotarget.extropos.data.local.entity.MenuItemEntity> items = null;
    
    public InMemoryMenuItemDao() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAllAvailable(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.MenuItemEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String categoryId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.MenuItemEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.data.local.entity.MenuItemEntity> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object searchByName(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.MenuItemEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object upsert(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.MenuItemEntity[] items, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateAvailability(@org.jetbrains.annotations.NotNull()
    java.lang.String id, boolean isAvailable, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void clearAll() {
    }
}