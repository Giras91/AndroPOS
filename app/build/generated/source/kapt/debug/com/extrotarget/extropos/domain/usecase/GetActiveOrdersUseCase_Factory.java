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
public final class GetActiveOrdersUseCase_Factory implements Factory<GetActiveOrdersUseCase> {
  private final Provider<IOrderRepository> orderRepositoryProvider;

  public GetActiveOrdersUseCase_Factory(Provider<IOrderRepository> orderRepositoryProvider) {
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public GetActiveOrdersUseCase get() {
    return newInstance(orderRepositoryProvider.get());
  }

  public static GetActiveOrdersUseCase_Factory create(
      Provider<IOrderRepository> orderRepositoryProvider) {
    return new GetActiveOrdersUseCase_Factory(orderRepositoryProvider);
  }

  public static GetActiveOrdersUseCase newInstance(IOrderRepository orderRepository) {
    return new GetActiveOrdersUseCase(orderRepository);
  }
}
