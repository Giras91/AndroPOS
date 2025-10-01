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
    "KotlinInternalInJava"
})
public final class UpdateOrderStatusUseCase_Factory implements Factory<UpdateOrderStatusUseCase> {
  private final Provider<IOrderRepository> orderRepositoryProvider;

  public UpdateOrderStatusUseCase_Factory(Provider<IOrderRepository> orderRepositoryProvider) {
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public UpdateOrderStatusUseCase get() {
    return newInstance(orderRepositoryProvider.get());
  }

  public static UpdateOrderStatusUseCase_Factory create(
      Provider<IOrderRepository> orderRepositoryProvider) {
    return new UpdateOrderStatusUseCase_Factory(orderRepositoryProvider);
  }

  public static UpdateOrderStatusUseCase newInstance(IOrderRepository orderRepository) {
    return new UpdateOrderStatusUseCase(orderRepository);
  }
}
