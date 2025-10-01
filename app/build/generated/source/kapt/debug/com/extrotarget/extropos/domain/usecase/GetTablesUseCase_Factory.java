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
public final class GetTablesUseCase_Factory implements Factory<GetTablesUseCase> {
  private final Provider<ITableRepository> tableRepositoryProvider;

  public GetTablesUseCase_Factory(Provider<ITableRepository> tableRepositoryProvider) {
    this.tableRepositoryProvider = tableRepositoryProvider;
  }

  @Override
  public GetTablesUseCase get() {
    return newInstance(tableRepositoryProvider.get());
  }

  public static GetTablesUseCase_Factory create(
      Provider<ITableRepository> tableRepositoryProvider) {
    return new GetTablesUseCase_Factory(tableRepositoryProvider);
  }

  public static GetTablesUseCase newInstance(ITableRepository tableRepository) {
    return new GetTablesUseCase(tableRepository);
  }
}
