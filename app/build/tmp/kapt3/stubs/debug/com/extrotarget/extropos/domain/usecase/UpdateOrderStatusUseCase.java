package com.extrotarget.extropos.domain.usecase;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086B\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/extrotarget/extropos/domain/usecase/UpdateOrderStatusUseCase;", "", "orderRepository", "Lcom/extrotarget/extropos/domain/repository/IOrderRepository;", "(Lcom/extrotarget/extropos/domain/repository/IOrderRepository;)V", "invoke", "", "orderId", "", "status", "Lcom/extrotarget/extropos/domain/model/OrderStatus;", "(Ljava/lang/String;Lcom/extrotarget/extropos/domain/model/OrderStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UpdateOrderStatusUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.repository.IOrderRepository orderRepository = null;
    
    @javax.inject.Inject()
    public UpdateOrderStatusUseCase(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.repository.IOrderRepository orderRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.OrderStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}