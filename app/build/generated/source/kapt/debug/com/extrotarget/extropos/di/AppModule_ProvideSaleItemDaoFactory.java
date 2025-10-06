package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.dao.SaleItemDao;
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
public final class AppModule_ProvideSaleItemDaoFactory implements Factory<SaleItemDao> {
  @Override
  public SaleItemDao get() {
    return provideSaleItemDao();
  }

  public static AppModule_ProvideSaleItemDaoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SaleItemDao provideSaleItemDao() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSaleItemDao());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideSaleItemDaoFactory INSTANCE = new AppModule_ProvideSaleItemDaoFactory();
  }
}
