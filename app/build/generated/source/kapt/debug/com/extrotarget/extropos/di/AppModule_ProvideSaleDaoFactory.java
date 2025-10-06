package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.dao.SaleDao;
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
public final class AppModule_ProvideSaleDaoFactory implements Factory<SaleDao> {
  @Override
  public SaleDao get() {
    return provideSaleDao();
  }

  public static AppModule_ProvideSaleDaoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SaleDao provideSaleDao() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSaleDao());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideSaleDaoFactory INSTANCE = new AppModule_ProvideSaleDaoFactory();
  }
}
