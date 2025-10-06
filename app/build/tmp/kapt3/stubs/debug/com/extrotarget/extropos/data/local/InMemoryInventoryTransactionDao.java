package com.extrotarget.extropos.data.local;

/**
 * In-memory implementation of InventoryTransactionDao.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\u0006\u0010\u0013\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0015J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0096@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u001dJ\u001c\u0010\u001e\u001a\u00020\f2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\nH\u0096@\u00a2\u0006\u0002\u0010\u001fJ\u001c\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0!2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/extrotarget/extropos/data/local/InMemoryInventoryTransactionDao;", "Lcom/extrotarget/extropos/data/local/dao/InventoryTransactionDao;", "()V", "nextId", "", "transactions", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/extrotarget/extropos/data/local/entity/InventoryTransactionEntity;", "transactionsFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByDateRange", "startTime", "endTime", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByProductId", "productId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByType", "type", "limit", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "transaction", "(Lcom/extrotarget/extropos/data/local/entity/InventoryTransactionEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeByProductId", "Lkotlinx/coroutines/flow/Flow;", "app_debug"})
public final class InMemoryInventoryTransactionDao implements com.extrotarget.extropos.data.local.dao.InventoryTransactionDao {
    @org.jetbrains.annotations.NotNull()
    private final java.util.concurrent.ConcurrentHashMap<java.lang.Long, com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity> transactions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity>> transactionsFlow = null;
    private long nextId = 1L;
    
    public InMemoryInventoryTransactionDao() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getByProductId(@org.jetbrains.annotations.NotNull()
    java.lang.String productId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity>> observeByProductId(@org.jetbrains.annotations.NotNull()
    java.lang.String productId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getByType(@org.jetbrains.annotations.NotNull()
    java.lang.String type, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity> transactions, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getByDateRange(long startTime, long endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity>> $completion) {
        return null;
    }
}