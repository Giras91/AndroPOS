package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.dao.InventoryTransactionDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class AppModule_ProvideInventoryTransactionDaoFactory implements Factory<InventoryTransactionDao> {
  @Override
  public InventoryTransactionDao get() {
    return provideInventoryTransactionDao();
  }

  public static AppModule_ProvideInventoryTransactionDaoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static InventoryTransactionDao provideInventoryTransactionDao() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideInventoryTransactionDao());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideInventoryTransactionDaoFactory INSTANCE = new AppModule_ProvideInventoryTransactionDaoFactory();
  }
}
