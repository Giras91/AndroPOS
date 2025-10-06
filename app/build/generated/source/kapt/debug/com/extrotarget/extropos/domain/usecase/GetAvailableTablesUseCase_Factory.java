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
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class GetAvailableTablesUseCase_Factory implements Factory<GetAvailableTablesUseCase> {
  private final Provider<ITableRepository> tableRepositoryProvider;

  public GetAvailableTablesUseCase_Factory(Provider<ITableRepository> tableRepositoryProvider) {
    this.tableRepositoryProvider = tableRepositoryProvider;
  }

  @Override
  public GetAvailableTablesUseCase get() {
    return newInstance(tableRepositoryProvider.get());
  }

  public static GetAvailableTablesUseCase_Factory create(
      Provider<ITableRepository> tableRepositoryProvider) {
    return new GetAvailableTablesUseCase_Factory(tableRepositoryProvider);
  }

  public static GetAvailableTablesUseCase newInstance(ITableRepository tableRepository) {
    return new GetAvailableTablesUseCase(tableRepository);
  }
}
