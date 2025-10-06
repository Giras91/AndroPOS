package com.extrotarget.extropos.data.local;

/**
 * In-memory implementation of PaymentDao.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0011J,\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0096@\u00a2\u0006\u0002\u0010\u0016J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u001aJ\u001c\u0010\u001b\u001a\u00020\f2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\nH\u0096@\u00a2\u0006\u0002\u0010\u001cJ\u001c\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\u001e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/extrotarget/extropos/data/local/InMemoryPaymentDao;", "Lcom/extrotarget/extropos/data/local/dao/PaymentDao;", "()V", "nextId", "", "payments", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/extrotarget/extropos/data/local/entity/PaymentEntity;", "paymentsFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllForSale", "saleId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByMethodAndDateRange", "method", "startTime", "endTime", "(Ljava/lang/String;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBySaleId", "insert", "payment", "(Lcom/extrotarget/extropos/data/local/entity/PaymentEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeBySaleId", "Lkotlinx/coroutines/flow/Flow;", "update", "app_debug"})
public final class InMemoryPaymentDao implements com.extrotarget.extropos.data.local.dao.PaymentDao {
    @org.jetbrains.annotations.NotNull()
    private final java.util.concurrent.ConcurrentHashMap<java.lang.Long, com.extrotarget.extropos.data.local.entity.PaymentEntity> payments = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.extrotarget.extropos.data.local.entity.PaymentEntity>> paymentsFlow = null;
    private long nextId = 1L;
    
    public InMemoryPaymentDao() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getBySaleId(@org.jetbrains.annotations.NotNull()
    java.lang.String saleId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.PaymentEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.extrotarget.extropos.data.local.entity.PaymentEntity>> observeBySaleId(@org.jetbrains.annotations.NotNull()
    java.lang.String saleId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getByMethodAndDateRange(@org.jetbrains.annotations.NotNull()
    java.lang.String method, long startTime, long endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.PaymentEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.PaymentEntity payment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.extrotarget.extropos.data.local.entity.PaymentEntity> payments, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.PaymentEntity payment, @org.jetbrains.annotations.NotNull()
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