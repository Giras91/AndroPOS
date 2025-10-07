package com.extrotarget.extropos.training;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0003H\u0002J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0096@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0014\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0096@\u00a2\u0006\u0002\u0010\u0019J\"\u0010\u001a\u001a\u00020\u00162\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u001c\"\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/extrotarget/extropos/training/DelegatingMenuItemDao;", "Lcom/extrotarget/extropos/data/local/dao/MenuItemDao;", "live", "Lcom/extrotarget/extropos/data/local/InMemoryMenuItemDao;", "training", "mode", "Lcom/extrotarget/extropos/training/TrainingModeManager;", "(Lcom/extrotarget/extropos/data/local/InMemoryMenuItemDao;Lcom/extrotarget/extropos/data/local/InMemoryMenuItemDao;Lcom/extrotarget/extropos/training/TrainingModeManager;)V", "active", "getAllAvailable", "", "Lcom/extrotarget/extropos/data/local/entity/MenuItemEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByCategory", "categoryId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "searchByName", "query", "updateAvailability", "", "isAvailable", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsert", "items", "", "([Lcom/extrotarget/extropos/data/local/entity/MenuItemEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class DelegatingMenuItemDao implements com.extrotarget.extropos.data.local.dao.MenuItemDao {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.InMemoryMenuItemDao live = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.InMemoryMenuItemDao training = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.training.TrainingModeManager mode = null;
    
    public DelegatingMenuItemDao(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.InMemoryMenuItemDao live, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.InMemoryMenuItemDao training, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.training.TrainingModeManager mode) {
        super();
    }
    
    private final com.extrotarget.extropos.data.local.InMemoryMenuItemDao active() {
        return null;
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
}