package com.extrotarget.extropos.training;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0003H\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u00142\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0015J\"\u0010\u0016\u001a\u00020\n2\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0018\"\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/extrotarget/extropos/training/DelegatingOrderItemDao;", "Lcom/extrotarget/extropos/data/local/dao/OrderItemDao;", "live", "Lcom/extrotarget/extropos/data/local/InMemoryOrderItemDao;", "training", "mode", "Lcom/extrotarget/extropos/training/TrainingModeManager;", "(Lcom/extrotarget/extropos/data/local/InMemoryOrderItemDao;Lcom/extrotarget/extropos/data/local/InMemoryOrderItemDao;Lcom/extrotarget/extropos/training/TrainingModeManager;)V", "active", "delete", "", "item", "Lcom/extrotarget/extropos/data/local/entity/OrderItemEntity;", "(Lcom/extrotarget/extropos/data/local/entity/OrderItemEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteById", "orderId", "", "itemId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByOrderId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "items", "", "([Lcom/extrotarget/extropos/data/local/entity/OrderItemEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "app_debug"})
public final class DelegatingOrderItemDao implements com.extrotarget.extropos.data.local.dao.OrderItemDao {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.InMemoryOrderItemDao live = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.InMemoryOrderItemDao training = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.training.TrainingModeManager mode = null;
    
    public DelegatingOrderItemDao(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.InMemoryOrderItemDao live, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.InMemoryOrderItemDao training, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.training.TrainingModeManager mode) {
        super();
    }
    
    private final com.extrotarget.extropos.data.local.InMemoryOrderItemDao active() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getByOrderId(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.OrderItemEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.OrderItemEntity[] items, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.OrderItemEntity item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.OrderItemEntity item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteById(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    java.lang.String itemId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}