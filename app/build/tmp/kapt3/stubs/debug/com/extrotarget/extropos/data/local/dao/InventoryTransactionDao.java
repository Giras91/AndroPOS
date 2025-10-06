package com.extrotarget.extropos.data.local.dao;

/**
 * DAO for InventoryTransactionEntity - stock tracking.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0011\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\u00020\u00032\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u001c\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u001c2\u0006\u0010\r\u001a\u00020\u000eH\'\u00a8\u0006\u001d"}, d2 = {"Lcom/extrotarget/extropos/data/local/dao/InventoryTransactionDao;", "", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByDateRange", "", "Lcom/extrotarget/extropos/data/local/entity/InventoryTransactionEntity;", "startTime", "", "endTime", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByProductId", "productId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByType", "type", "limit", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "transaction", "(Lcom/extrotarget/extropos/data/local/entity/InventoryTransactionEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "transactions", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeByProductId", "Lkotlinx/coroutines/flow/Flow;", "app_debug"})
@androidx.room.Dao()
public abstract interface InventoryTransactionDao {
    
    @androidx.room.Query(value = "SELECT * FROM inventory_transactions WHERE productId = :productId ORDER BY createdAt DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByProductId(@org.jetbrains.annotations.NotNull()
    java.lang.String productId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM inventory_transactions WHERE productId = :productId ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity>> observeByProductId(@org.jetbrains.annotations.NotNull()
    java.lang.String productId);
    
    @androidx.room.Query(value = "SELECT * FROM inventory_transactions WHERE type = :type ORDER BY createdAt DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByType(@org.jetbrains.annotations.NotNull()
    java.lang.String type, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity> transactions, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM inventory_transactions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM inventory_transactions WHERE createdAt BETWEEN :startTime AND :endTime ORDER BY createdAt DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByDateRange(long startTime, long endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity>> $completion);
    
    /**
     * DAO for InventoryTransactionEntity - stock tracking.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}