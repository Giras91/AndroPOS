package com.extrotarget.extropos.domain.usecase;

import com.extrotarget.extropos.domain.repository.IOrderRepository;
import com.extrotarget.extropos.domain.repository.ITableRepository;
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
public final class AssignTableToOrderUseCase_Factory implements Factory<AssignTableToOrderUseCase> {
  private final Provider<ITableRepository> tableRepositoryProvider;

  private final Provider<IOrderRepository> orderRepositoryProvider;

  public AssignTableToOrderUseCase_Factory(Provider<ITableRepository> tableRepositoryProvider,
      Provider<IOrderRepository> orderRepositoryProvider) {
    this.tableRepositoryProvider = tableRepositoryProvider;
    this.orderRepositoryProvider = orderRepositoryProvider;
  }

  @Override
  public AssignTableToOrderUseCase get() {
    return newInstance(tableRepositoryProvider.get(), orderRepositoryProvider.get());
  }

  public static AssignTableToOrderUseCase_Factory create(
      Provider<ITableRepository> tableRepositoryProvider,
      Provider<IOrderRepository> orderRepositoryProvider) {
    return new AssignTableToOrderUseCase_Factory(tableRepositoryProvider, orderRepositoryProvider);
  }

  public static AssignTableToOrderUseCase newInstance(ITableRepository tableRepository,
      IOrderRepository orderRepository) {
    return new AssignTableToOrderUseCase(tableRepository, orderRepository);
  }
}
