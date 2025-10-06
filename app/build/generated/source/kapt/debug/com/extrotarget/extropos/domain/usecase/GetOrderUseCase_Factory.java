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
public final class GetOrderUseCase_Factory implements Factory<GetOrderUseCase> {
  private final Provider<IOrderRepository> orderRepositoryProvider;

  public GetOrderUseCase_Factory(Provider<IOrderRepository> orderRepositoryProvider) {
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public GetOrderUseCase get() {
    return newInstance(orderRepositoryProvider.get());
  }

  public static GetOrderUseCase_Factory create(Provider<IOrderRepository> orderRepositoryProvider) {
    return new GetOrderUseCase_Factory(orderRepositoryProvider);
  }

  public static GetOrderUseCase newInstance(IOrderRepository orderRepository) {
    return new GetOrderUseCase(orderRepository);
  }
}
