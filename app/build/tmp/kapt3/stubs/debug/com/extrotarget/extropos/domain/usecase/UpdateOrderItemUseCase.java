package com.extrotarget.extropos.domain.usecase;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J&\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086B\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\u0005\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086B\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/extrotarget/extropos/domain/usecase/UpdateOrderItemUseCase;", "", "orderRepository", "Lcom/extrotarget/extropos/domain/repository/IOrderRepository;", "(Lcom/extrotarget/extropos/domain/repository/IOrderRepository;)V", "invoke", "Lcom/extrotarget/extropos/domain/model/Order;", "order", "itemId", "", "updatedItem", "Lcom/extrotarget/extropos/domain/model/OrderItem;", "(Lcom/extrotarget/extropos/domain/model/Order;Ljava/lang/String;Lcom/extrotarget/extropos/domain/model/OrderItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "orderId", "(Ljava/lang/String;Lcom/extrotarget/extropos/domain/model/OrderItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UpdateOrderItemUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.repository.IOrderRepository orderRepository = null;
    
    @javax.inject.Inject()
    public UpdateOrderItemUseCase(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.repository.IOrderRepository orderRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.Order order, @org.jetbrains.annotations.NotNull()
    java.lang.String itemId, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.OrderItem updatedItem, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.Order> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.OrderItem updatedItem, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}