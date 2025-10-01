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
public final class CreateOrderUseCase_Factory implements Factory<CreateOrderUseCase> {
  private final Provider<IOrderRepository> orderRepositoryProvider;

  public CreateOrderUseCase_Factory(Provider<IOrderRepository> orderRepositoryProvider) {
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public CreateOrderUseCase get() {
    return newInstance(orderRepositoryProvider.get());
  }

  public static CreateOrderUseCase_Factory create(
      Provider<IOrderRepository> orderRepositoryProvider) {
    return new CreateOrderUseCase_Factory(orderRepositoryProvider);
  }

  public static CreateOrderUseCase newInstance(IOrderRepository orderRepository) {
    return new CreateOrderUseCase(orderRepository);
  }
}
