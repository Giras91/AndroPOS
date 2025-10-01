package com.extrotarget.extropos.ui.order;

import com.extrotarget.extropos.domain.usecase.AddItemToOrderUseCase;
import com.extrotarget.extropos.domain.usecase.CreateOrderUseCase;
import com.extrotarget.extropos.domain.usecase.GetActiveOrdersUseCase;
import com.extrotarget.extropos.domain.usecase.GetOrderUseCase;
import com.extrotarget.extropos.domain.usecase.RemoveOrderItemUseCase;
import com.extrotarget.extropos.domain.usecase.UpdateOrderItemUseCase;
import com.extrotarget.extropos.domain.usecase.UpdateOrderStatusUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class OrderViewModel_Factory implements Factory<OrderViewModel> {
  private final Provider<CreateOrderUseCase> createOrderProvider;

  private final Provider<GetOrderUseCase> getOrderProvider;

  private final Provider<UpdateOrderStatusUseCase> updateOrderStatusProvider;

  private final Provider<AddItemToOrderUseCase> addItemToOrderProvider;

  private final Provider<UpdateOrderItemUseCase> updateOrderItemProvider;

  private final Provider<RemoveOrderItemUseCase> removeOrderItemProvider;

  private final Provider<GetActiveOrdersUseCase> getActiveOrdersProvider;

  public OrderViewModel_Factory(Provider<CreateOrderUseCase> createOrderProvider,
      Provider<GetOrderUseCase> getOrderProvider,
      Provider<UpdateOrderStatusUseCase> updateOrderStatusProvider,
      Provider<AddItemToOrderUseCase> addItemToOrderProvider,
      Provider<UpdateOrderItemUseCase> updateOrderItemProvider,
      Provider<RemoveOrderItemUseCase> removeOrderItemProvider,
      Provider<GetActiveOrdersUseCase> getActiveOrdersProvider) {
    this.createOrderProvider = createOrderProvider;
    this.getOrderProvider = getOrderProvider;
    this.updateOrderStatusProvider = updateOrderStatusProvider;
    this.addItemToOrderProvider = addItemToOrderProvider;
    this.updateOrderItemProvider = updateOrderItemProvider;
    this.removeOrderItemProvider = removeOrderItemProvider;
    this.getActiveOrdersProvider = getActiveOrdersProvider;
  }

  @Override
  public OrderViewModel get() {
    return newInstance(createOrderProvider.get(), getOrderProvider.get(), updateOrderStatusProvider.get(), addItemToOrderProvider.get(), updateOrderItemProvider.get(), removeOrderItemProvider.get(), getActiveOrdersProvider.get());
  }

  public static OrderViewModel_Factory create(Provider<CreateOrderUseCase> createOrderProvider,
      Provider<GetOrderUseCase> getOrderProvider,
      Provider<UpdateOrderStatusUseCase> updateOrderStatusProvider,
      Provider<AddItemToOrderUseCase> addItemToOrderProvider,
      Provider<UpdateOrderItemUseCase> updateOrderItemProvider,
      Provider<RemoveOrderItemUseCase> removeOrderItemProvider,
      Provider<GetActiveOrdersUseCase> getActiveOrdersProvider) {
    return new OrderViewModel_Factory(createOrderProvider, getOrderProvider, updateOrderStatusProvider, addItemToOrderProvider, updateOrderItemProvider, removeOrderItemProvider, getActiveOrdersProvider);
  }

  public static OrderViewModel newInstance(CreateOrderUseCase createOrder, GetOrderUseCase getOrder,
      UpdateOrderStatusUseCase updateOrderStatus, AddItemToOrderUseCase addItemToOrder,
      UpdateOrderItemUseCase updateOrderItem, RemoveOrderItemUseCase removeOrderItem,
      GetActiveOrdersUseCase getActiveOrders) {
    return new OrderViewModel(createOrder, getOrder, updateOrderStatus, addItemToOrder, updateOrderItem, removeOrderItem, getActiveOrders);
  }
}
