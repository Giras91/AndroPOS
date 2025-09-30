package com.extrotarget.extropos.ui.order;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J2\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020)2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0017J\u0006\u0010+\u001a\u00020#J\u0018\u0010,\u001a\u00020\u00172\u0006\u0010-\u001a\u00020\u00172\b\b\u0002\u0010.\u001a\u00020/J\u0006\u00100\u001a\u00020)J\u0006\u00101\u001a\u00020)J\u0006\u00102\u001a\u00020)J\u0006\u00103\u001a\u00020#J\u000e\u00104\u001a\u00020#2\u0006\u00105\u001a\u00020\u0017J\u0016\u00106\u001a\u00020#2\u0006\u00105\u001a\u00020\u00172\u0006\u00107\u001a\u00020\u0017J\u0016\u0010\n\u001a\u00020#2\u0006\u00105\u001a\u00020\u00172\u0006\u00108\u001a\u000209J\u0016\u0010:\u001a\u00020#2\u0006\u00105\u001a\u00020\u00172\u0006\u0010;\u001a\u00020<R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u001cR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00190\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006="}, d2 = {"Lcom/extrotarget/extropos/ui/order/OrderViewModel;", "Landroidx/lifecycle/ViewModel;", "createOrder", "Lcom/extrotarget/extropos/domain/usecase/CreateOrderUseCase;", "getOrder", "Lcom/extrotarget/extropos/domain/usecase/GetOrderUseCase;", "updateOrder", "Lcom/extrotarget/extropos/domain/usecase/UpdateOrderUseCase;", "addItemToOrder", "Lcom/extrotarget/extropos/domain/usecase/AddItemToOrderUseCase;", "updateOrderItem", "Lcom/extrotarget/extropos/domain/usecase/UpdateOrderItemUseCase;", "removeOrderItem", "Lcom/extrotarget/extropos/domain/usecase/RemoveOrderItemUseCase;", "getActiveOrders", "Lcom/extrotarget/extropos/domain/usecase/GetActiveOrdersUseCase;", "(Lcom/extrotarget/extropos/domain/usecase/CreateOrderUseCase;Lcom/extrotarget/extropos/domain/usecase/GetOrderUseCase;Lcom/extrotarget/extropos/domain/usecase/UpdateOrderUseCase;Lcom/extrotarget/extropos/domain/usecase/AddItemToOrderUseCase;Lcom/extrotarget/extropos/domain/usecase/UpdateOrderItemUseCase;Lcom/extrotarget/extropos/domain/usecase/RemoveOrderItemUseCase;Lcom/extrotarget/extropos/domain/usecase/GetActiveOrdersUseCase;)V", "_activeOrders", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/extrotarget/extropos/domain/model/Order;", "_currentOrder", "_error", "", "_isLoading", "", "activeOrders", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "currentOrder", "getCurrentOrder", "error", "getError", "isLoading", "addItemToCurrentOrder", "", "menuItemId", "menuItemName", "quantity", "", "unitPrice", "", "notes", "clearCurrentOrder", "createNewOrder", "tableId", "orderType", "Lcom/extrotarget/extropos/domain/model/OrderType;", "getOrderSubtotal", "getOrderTax", "getOrderTotal", "loadActiveOrders", "loadOrder", "orderId", "removeItemFromOrder", "itemId", "item", "Lcom/extrotarget/extropos/domain/model/OrderItem;", "updateOrderStatus", "status", "Lcom/extrotarget/extropos/domain/model/OrderStatus;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class OrderViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.CreateOrderUseCase createOrder = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.GetOrderUseCase getOrder = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.UpdateOrderUseCase updateOrder = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.AddItemToOrderUseCase addItemToOrder = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.UpdateOrderItemUseCase updateOrderItem = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.RemoveOrderItemUseCase removeOrderItem = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.GetActiveOrdersUseCase getActiveOrders = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.extrotarget.extropos.domain.model.Order> _currentOrder = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.extrotarget.extropos.domain.model.Order> currentOrder = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.extrotarget.extropos.domain.model.Order>> _activeOrders = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.Order>> activeOrders = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    
    @javax.inject.Inject()
    public OrderViewModel(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.CreateOrderUseCase createOrder, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.GetOrderUseCase getOrder, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.UpdateOrderUseCase updateOrder, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.AddItemToOrderUseCase addItemToOrder, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.UpdateOrderItemUseCase updateOrderItem, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.RemoveOrderItemUseCase removeOrderItem, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.GetActiveOrdersUseCase getActiveOrders) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.extrotarget.extropos.domain.model.Order> getCurrentOrder() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.Order>> getActiveOrders() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String createNewOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String tableId, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.OrderType orderType) {
        return null;
    }
    
    public final void loadOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId) {
    }
    
    public final void addItemToCurrentOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String menuItemId, @org.jetbrains.annotations.NotNull()
    java.lang.String menuItemName, int quantity, long unitPrice, @org.jetbrains.annotations.Nullable()
    java.lang.String notes) {
    }
    
    public final void updateOrderItem(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.OrderItem item) {
    }
    
    public final void removeItemFromOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    java.lang.String itemId) {
    }
    
    public final void updateOrderStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.OrderStatus status) {
    }
    
    public final void loadActiveOrders() {
    }
    
    public final void clearCurrentOrder() {
    }
    
    public final long getOrderTotal() {
        return 0L;
    }
    
    public final long getOrderSubtotal() {
        return 0L;
    }
    
    public final long getOrderTax() {
        return 0L;
    }
}