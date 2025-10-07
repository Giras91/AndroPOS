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
public final class RemoveOrderItemUseCase_Factory implements Factory<RemoveOrderItemUseCase> {
  private final Provider<IOrderRepository> orderRepositoryProvider;

  public RemoveOrderItemUseCase_Factory(Provider<IOrderRepository> orderRepositoryProvider) {
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public RemoveOrderItemUseCase get() {
    return newInstance(orderRepositoryProvider.get());
  }

  public static RemoveOrderItemUseCase_Factory create(
      Provider<IOrderRepository> orderRepositoryProvider) {
    return new RemoveOrderItemUseCase_Factory(orderRepositoryProvider);
  }

  public static RemoveOrderItemUseCase newInstance(IOrderRepository orderRepository) {
    return new RemoveOrderItemUseCase(orderRepository);
  }
}
