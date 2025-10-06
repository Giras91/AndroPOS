package com.extrotarget.extropos.data.local.dao;

/**
 * Extended DAO for SaleEntity with comprehensive querying.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ,\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0018\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ&\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u001a\u001a\u00020\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u00a7@\u00a2\u0006\u0002\u0010\u001dJ\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u00a7@\u00a2\u0006\u0002\u0010\u001fJ \u0010 \u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001e\u0010!\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010$J\u001c\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0&2\u0006\u0010\'\u001a\u00020(H\'J\u0016\u0010)\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010$\u00a8\u0006*"}, d2 = {"Lcom/extrotarget/extropos/data/local/dao/SaleDao;", "", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllTraining", "getByCustomerId", "", "Lcom/extrotarget/extropos/data/local/entity/SaleEntity;", "customerId", "getByDateRange", "startTime", "", "endTime", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "getByPaymentMethod", "method", "(Ljava/lang/String;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByReceiptNo", "receiptNo", "getByUserId", "userId", "limit", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecent", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalSales", "getTransactionCount", "insert", "sale", "(Lcom/extrotarget/extropos/data/local/entity/SaleEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeByTrainingMode", "Lkotlinx/coroutines/flow/Flow;", "isTraining", "", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface SaleDao {
    
    @androidx.room.Query(value = "SELECT * FROM sales ORDER BY createdAt DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRecent(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.SaleEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM sales WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.data.local.entity.SaleEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM sales WHERE receiptNo = :receiptNo LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByReceiptNo(@org.jetbrains.annotations.NotNull()
    java.lang.String receiptNo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.data.local.entity.SaleEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM sales WHERE createdAt BETWEEN :startTime AND :endTime ORDER BY createdAt DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByDateRange(long startTime, long endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.SaleEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM sales WHERE customerId = :customerId ORDER BY createdAt DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByCustomerId(@org.jetbrains.annotations.NotNull()
    java.lang.String customerId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.SaleEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM sales WHERE userId = :userId ORDER BY createdAt DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByUserId(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.SaleEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM sales WHERE paymentMethod = :method AND createdAt BETWEEN :startTime AND :endTime")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByPaymentMethod(@org.jetbrains.annotations.NotNull()
    java.lang.String method, long startTime, long endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.SaleEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM sales WHERE isTraining = :isTraining ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.extrotarget.extropos.data.local.entity.SaleEntity>> observeByTrainingMode(boolean isTraining);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.SaleEntity sale, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.SaleEntity sale, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM sales WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM sales WHERE isTraining = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllTraining(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM sales")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(totalAmountCents) FROM sales WHERE createdAt BETWEEN :startTime AND :endTime AND paymentStatus = \'PAID\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalSales(long startTime, long endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM sales WHERE createdAt BETWEEN :startTime AND :endTime")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTransactionCount(long startTime, long endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Extended DAO for SaleEntity with comprehensive querying.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}