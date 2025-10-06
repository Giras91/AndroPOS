package com.extrotarget.extropos.data.repository;

import com.extrotarget.extropos.data.local.dao.TableDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class TableRepository_Factory implements Factory<TableRepository> {
  private final Provider<TableDao> tableDaoProvider;

  public TableRepository_Factory(Provider<TableDao> tableDaoProvider) {
    this.tableDaoProvider = tableDaoProvider;
  }

  @Override
  public TableRepository get() {
    return newInstance(tableDaoProvider.get());
  }

  public static TableRepository_Factory create(Provider<TableDao> tableDaoProvider) {
    return new TableRepository_Factory(tableDaoProvider);
  }

  public static TableRepository newInstance(TableDao tableDao) {
    return new TableRepository(tableDao);
  }
}
