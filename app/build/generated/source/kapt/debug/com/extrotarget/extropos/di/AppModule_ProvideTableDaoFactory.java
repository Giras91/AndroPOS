package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.dao.TableDao;
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
public final class AppModule_ProvideTableDaoFactory implements Factory<TableDao> {
  @Override
  public TableDao get() {
    return provideTableDao();
  }

  public static AppModule_ProvideTableDaoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TableDao provideTableDao() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTableDao());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideTableDaoFactory INSTANCE = new AppModule_ProvideTableDaoFactory();
  }
}
