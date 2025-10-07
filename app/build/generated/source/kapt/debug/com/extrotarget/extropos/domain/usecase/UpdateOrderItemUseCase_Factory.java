package com.extrotarget.extropos.domain.usecase;

import com.extrotarget.extropos.domain.repository.IOrderRepository;
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
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class UpdateOrderItemUseCase_Factory implements Factory<UpdateOrderItemUseCase> {
  private final Provider<IOrderRepository> orderRepositoryProvider;

  public UpdateOrderItemUseCase_Factory(Provider<IOrderRepository> orderRepositoryProvider) {
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public UpdateOrderItemUseCase get() {
    return newInstance(orderRepositoryProvider.get());
  }

  public static UpdateOrderItemUseCase_Factory create(
      Provider<IOrderRepository> orderRepositoryProvider) {
    return new UpdateOrderItemUseCase_Factory(orderRepositoryProvider);
  }

  public static UpdateOrderItemUseCase newInstance(IOrderRepository orderRepository) {
    return new UpdateOrderItemUseCase(orderRepository);
  }
}
