package com.extrotarget.extropos.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u0013H\u0096@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0016\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u00132\u0006\u0010\u001d\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u0017J\u001e\u0010\u001e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0011J\u001e\u0010\"\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u001e\u0010#\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aH\u0096@\u00a2\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0082@\u00a2\u0006\u0002\u0010\u0017J\u0012\u0010&\u001a\u00020\u0010*\u00020\'H\u0082@\u00a2\u0006\u0002\u0010(J\f\u0010&\u001a\u00020\f*\u00020)H\u0002J\f\u0010*\u001a\u00020\'*\u00020\u0010H\u0002J\u0014\u0010*\u001a\u00020)*\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/extrotarget/extropos/data/repository/OrderRepository;", "Lcom/extrotarget/extropos/domain/repository/IOrderRepository;", "orderDao", "Lcom/extrotarget/extropos/data/local/dao/OrderDao;", "orderItemDao", "Lcom/extrotarget/extropos/data/local/dao/OrderItemDao;", "(Lcom/extrotarget/extropos/data/local/dao/OrderDao;Lcom/extrotarget/extropos/data/local/dao/OrderItemDao;)V", "addItemToOrder", "", "orderId", "", "item", "Lcom/extrotarget/extropos/domain/model/OrderItem;", "(Ljava/lang/String;Lcom/extrotarget/extropos/domain/model/OrderItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createOrder", "order", "Lcom/extrotarget/extropos/domain/model/Order;", "(Lcom/extrotarget/extropos/domain/model/Order;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveOrders", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrderById", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrdersByStatus", "status", "Lcom/extrotarget/extropos/domain/model/OrderStatus;", "(Lcom/extrotarget/extropos/domain/model/OrderStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrdersByTable", "tableId", "removeOrderItem", "itemId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateOrder", "updateOrderItem", "updateOrderStatus", "(Ljava/lang/String;Lcom/extrotarget/extropos/domain/model/OrderStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateOrderTotals", "toDomain", "Lcom/extrotarget/extropos/data/local/entity/OrderEntity;", "(Lcom/extrotarget/extropos/data/local/entity/OrderEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/extrotarget/extropos/data/local/entity/OrderItemEntity;", "toEntity", "app_debug"})
public final class OrderRepository implements com.extrotarget.extropos.domain.repository.IOrderRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.dao.OrderDao orderDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.dao.OrderItemDao orderItemDao = null;
    
    @javax.inject.Inject()
    public OrderRepository(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.dao.OrderDao orderDao, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.dao.OrderItemDao orderItemDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object createOrder(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.Order order, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getOrderById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.Order> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getOrdersByStatus(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.OrderStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.Order>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getOrdersByTable(@org.jetbrains.annotations.NotNull()
    java.lang.String tableId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.Order>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateOrder(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.Order order, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateOrderStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.OrderStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addItemToOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.OrderItem item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateOrderItem(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.OrderItem item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object removeOrderItem(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    java.lang.String itemId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getActiveOrders(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.Order>> $completion) {
        return null;
    }
    
    private final java.lang.Object updateOrderTotals(java.lang.String orderId, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object toDomain(com.extrotarget.extropos.data.local.entity.OrderEntity $this$toDomain, kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.Order> $completion) {
        return null;
    }
    
    private final com.extrotarget.extropos.data.local.entity.OrderEntity toEntity(com.extrotarget.extropos.domain.model.Order $this$toEntity) {
        return null;
    }
    
    private final com.extrotarget.extropos.domain.model.OrderItem toDomain(com.extrotarget.extropos.data.local.entity.OrderItemEntity $this$toDomain) {
        return null;
    }
    
    private final com.extrotarget.extropos.data.local.entity.OrderItemEntity toEntity(com.extrotarget.extropos.domain.model.OrderItem $this$toEntity, java.lang.String orderId) {
        return null;
    }
}