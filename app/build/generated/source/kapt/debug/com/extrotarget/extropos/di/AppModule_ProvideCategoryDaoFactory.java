package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.dao.CategoryDao;
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
public final class AppModule_ProvideCategoryDaoFactory implements Factory<CategoryDao> {
  @Override
  public CategoryDao get() {
    return provideCategoryDao();
  }

  public static AppModule_ProvideCategoryDaoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CategoryDao provideCategoryDao() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideCategoryDao());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideCategoryDaoFactory INSTANCE = new AppModule_ProvideCategoryDaoFactory();
  }
}
