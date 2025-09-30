package com.extrotarget.extropos.data.repository;

import com.extrotarget.extropos.data.local.dao.OrderDao;
import com.extrotarget.extropos.data.local.dao.OrderItemDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class OrderRepository_Factory implements Factory<OrderRepository> {
  private final Provider<OrderDao> orderDaoProvider;

  private final Provider<OrderItemDao> orderItemDaoProvider;

  public OrderRepository_Factory(Provider<OrderDao> orderDaoProvider,
      Provider<OrderItemDao> orderItemDaoProvider) {
    this.orderDaoProvider = orderDaoProvider;
    this.orderItemDaoProvider = orderItemDaoProvider;
  }

  @Override
  public OrderRepository get() {
    return newInstance(orderDaoProvider.get(), orderItemDaoProvider.get());
  }

  public static OrderRepository_Factory create(Provider<OrderDao> orderDaoProvider,
      Provider<OrderItemDao> orderItemDaoProvider) {
    return new OrderRepository_Factory(orderDaoProvider, orderItemDaoProvider);
  }

  public static OrderRepository newInstance(OrderDao orderDao, OrderItemDao orderItemDao) {
    return new OrderRepository(orderDao, orderItemDao);
  }
}
