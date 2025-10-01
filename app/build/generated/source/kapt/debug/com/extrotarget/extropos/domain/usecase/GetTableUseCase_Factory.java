package com.extrotarget.extropos.domain.usecase;

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
    "KotlinInternalInJava"
})
public final class GetTableUseCase_Factory implements Factory<GetTableUseCase> {
  private final Provider<ITableRepository> tableRepositoryProvider;

  public GetTableUseCase_Factory(Provider<ITableRepository> tableRepositoryProvider) {
    this.tableRepositoryProvider = tableRepositoryProvider;
  }

  @Override
  public GetTableUseCase get() {
    return newInstance(tableRepositoryProvider.get());
  }

  public static GetTableUseCase_Factory create(Provider<ITableRepository> tableRepositoryProvider) {
    return new GetTableUseCase_Factory(tableRepositoryProvider);
  }

  public static GetTableUseCase newInstance(ITableRepository tableRepository) {
    return new GetTableUseCase(tableRepository);
  }
}
