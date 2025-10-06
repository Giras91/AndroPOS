package com.extrotarget.extropos.data.local;

/**
 * In-memory implementation of SaleItemDao for sandboxed training mode.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0096@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0096@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010\u0017\u001a\u00020\f2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0096@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\u001a2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/extrotarget/extropos/data/local/InMemorySaleItemDao;", "Lcom/extrotarget/extropos/data/local/dao/SaleItemDao;", "()V", "items", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/extrotarget/extropos/data/local/entity/SaleItemEntity;", "itemsFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "nextId", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "item", "(Lcom/extrotarget/extropos/data/local/entity/SaleItemEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllForSale", "saleId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getItemsBySaleId", "insert", "insertAll", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeItemsBySaleId", "Lkotlinx/coroutines/flow/Flow;", "app_debug"})
public final class InMemorySaleItemDao implements com.extrotarget.extropos.data.local.dao.SaleItemDao {
    @org.jetbrains.annotations.NotNull()
    private final java.util.concurrent.ConcurrentHashMap<java.lang.Long, com.extrotarget.extropos.data.local.entity.SaleItemEntity> items = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.extrotarget.extropos.data.local.entity.SaleItemEntity>> itemsFlow = null;
    private long nextId = 1L;
    
    public InMemorySaleItemDao() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getItemsBySaleId(@org.jetbrains.annotations.NotNull()
    java.lang.String saleId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.SaleItemEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.extrotarget.extropos.data.local.entity.SaleItemEntity>> observeItemsBySaleId(@org.jetbrains.annotations.NotNull()
    java.lang.String saleId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.SaleItemEntity item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.extrotarget.extropos.data.local.entity.SaleItemEntity> items, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.SaleItemEntity item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteAllForSale(@org.jetbrains.annotations.NotNull()
    java.lang.String saleId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}