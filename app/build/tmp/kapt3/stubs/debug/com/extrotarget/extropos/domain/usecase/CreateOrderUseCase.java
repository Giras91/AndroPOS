package com.extrotarget.extropos.domain.usecase;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J$\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0086B\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/extrotarget/extropos/domain/usecase/CreateOrderUseCase;", "", "orderRepository", "Lcom/extrotarget/extropos/domain/repository/IOrderRepository;", "(Lcom/extrotarget/extropos/domain/repository/IOrderRepository;)V", "generateOrderNumber", "", "invoke", "tableId", "orderType", "Lcom/extrotarget/extropos/domain/model/OrderType;", "(Ljava/lang/String;Lcom/extrotarget/extropos/domain/model/OrderType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class CreateOrderUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.repository.IOrderRepository orderRepository = null;
    
    @javax.inject.Inject()
    public CreateOrderUseCase(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.repository.IOrderRepository orderRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.Nullable()
    java.lang.String tableId, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.OrderType orderType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final java.lang.String generateOrderNumber() {
        return null;
    }
}