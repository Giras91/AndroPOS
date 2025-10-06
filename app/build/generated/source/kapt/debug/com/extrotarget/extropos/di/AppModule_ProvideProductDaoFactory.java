package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.dao.ProductDao;
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
public final class AppModule_ProvideProductDaoFactory implements Factory<ProductDao> {
  @Override
  public ProductDao get() {
    return provideProductDao();
  }

  public static AppModule_ProvideProductDaoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ProductDao provideProductDao() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideProductDao());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideProductDaoFactory INSTANCE = new AppModule_ProvideProductDaoFactory();
  }
}
