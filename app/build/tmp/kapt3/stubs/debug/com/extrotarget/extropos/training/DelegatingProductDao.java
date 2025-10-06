package com.extrotarget.extropos.training;

/**
 * Delegates DAO calls to either live or training in-memory store based on TrainingModeManager.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0003H\u0002J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0096@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\"\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0014\"\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/extrotarget/extropos/training/DelegatingProductDao;", "Lcom/extrotarget/extropos/data/local/dao/ProductDao;", "live", "Lcom/extrotarget/extropos/data/local/InMemoryProductDao;", "training", "mode", "Lcom/extrotarget/extropos/training/TrainingModeManager;", "(Lcom/extrotarget/extropos/data/local/InMemoryProductDao;Lcom/extrotarget/extropos/data/local/InMemoryProductDao;Lcom/extrotarget/extropos/training/TrainingModeManager;)V", "active", "getAll", "", "Lcom/extrotarget/extropos/data/local/entity/ProductEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsert", "", "product", "", "([Lcom/extrotarget/extropos/data/local/entity/ProductEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class DelegatingProductDao implements com.extrotarget.extropos.data.local.dao.ProductDao {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.InMemoryProductDao live = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.InMemoryProductDao training = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.training.TrainingModeManager mode = null;
    
    public DelegatingProductDao(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.InMemoryProductDao live, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.InMemoryProductDao training, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.training.TrainingModeManager mode) {
        super();
    }
    
    private final com.extrotarget.extropos.data.local.InMemoryProductDao active() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.ProductEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object upsert(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.ProductEntity[] product, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.data.local.entity.ProductEntity> $completion) {
        return null;
    }
}