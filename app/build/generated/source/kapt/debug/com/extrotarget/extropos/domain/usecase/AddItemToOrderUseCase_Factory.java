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
public final class AddItemToOrderUseCase_Factory implements Factory<AddItemToOrderUseCase> {
  private final Provider<IOrderRepository> orderRepositoryProvider;

  public AddItemToOrderUseCase_Factory(Provider<IOrderRepository> orderRepositoryProvider) {
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public AddItemToOrderUseCase get() {
    return newInstance(orderRepositoryProvider.get());
  }

  public static AddItemToOrderUseCase_Factory create(
      Provider<IOrderRepository> orderRepositoryProvider) {
    return new AddItemToOrderUseCase_Factory(orderRepositoryProvider);
  }

  public static AddItemToOrderUseCase newInstance(IOrderRepository orderRepository) {
    return new AddItemToOrderUseCase(orderRepository);
  }
}
