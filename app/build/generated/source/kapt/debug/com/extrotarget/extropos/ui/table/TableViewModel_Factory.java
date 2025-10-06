package com.extrotarget.extropos.ui.table;

import com.extrotarget.extropos.domain.usecase.GetAvailableTablesUseCase;
import com.extrotarget.extropos.domain.usecase.GetOccupiedTablesUseCase;
import com.extrotarget.extropos.domain.usecase.GetTableUseCase;
import com.extrotarget.extropos.domain.usecase.GetTablesUseCase;
import com.extrotarget.extropos.domain.usecase.UpdateTableStatusUseCase;
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
public final class TableViewModel_Factory implements Factory<TableViewModel> {
  private final Provider<GetTablesUseCase> getTablesProvider;

  private final Provider<GetTableUseCase> getTableProvider;

  private final Provider<UpdateTableStatusUseCase> updateTableStatusProvider;

  private final Provider<GetAvailableTablesUseCase> getAvailableTablesProvider;

  private final Provider<GetOccupiedTablesUseCase> getOccupiedTablesProvider;

  public TableViewModel_Factory(Provider<GetTablesUseCase> getTablesProvider,
      Provider<GetTableUseCase> getTableProvider,
      Provider<UpdateTableStatusUseCase> updateTableStatusProvider,
      Provider<GetAvailableTablesUseCase> getAvailableTablesProvider,
      Provider<GetOccupiedTablesUseCase> getOccupiedTablesProvider) {
    this.getTablesProvider = getTablesProvider;
    this.getTableProvider = getTableProvider;
    this.updateTableStatusProvider = updateTableStatusProvider;
    this.getAvailableTablesProvider = getAvailableTablesProvider;
    this.getOccupiedTablesProvider = getOccupiedTablesProvider;
  }

  @Override
  public TableViewModel get() {
    return newInstance(getTablesProvider.get(), getTableProvider.get(), updateTableStatusProvider.get(), getAvailableTablesProvider.get(), getOccupiedTablesProvider.get());
  }

  public static TableViewModel_Factory create(Provider<GetTablesUseCase> getTablesProvider,
      Provider<GetTableUseCase> getTableProvider,
      Provider<UpdateTableStatusUseCase> updateTableStatusProvider,
      Provider<GetAvailableTablesUseCase> getAvailableTablesProvider,
      Provider<GetOccupiedTablesUseCase> getOccupiedTablesProvider) {
    return new TableViewModel_Factory(getTablesProvider, getTableProvider, updateTableStatusProvider, getAvailableTablesProvider, getOccupiedTablesProvider);
  }

  public static TableViewModel newInstance(GetTablesUseCase getTables, GetTableUseCase getTable,
      UpdateTableStatusUseCase updateTableStatus, GetAvailableTablesUseCase getAvailableTables,
      GetOccupiedTablesUseCase getOccupiedTables) {
    return new TableViewModel(getTables, getTable, updateTableStatus, getAvailableTables, getOccupiedTables);
  }
}
