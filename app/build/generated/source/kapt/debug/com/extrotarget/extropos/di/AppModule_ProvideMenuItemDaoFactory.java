package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.AppDatabase;
import com.extrotarget.extropos.data.local.dao.MenuItemDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideMenuItemDaoFactory implements Factory<MenuItemDao> {
  private final Provider<AppDatabase> dbProvider;

  public AppModule_ProvideMenuItemDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public MenuItemDao get() {
    return provideMenuItemDao(dbProvider.get());
  }

  public static AppModule_ProvideMenuItemDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new AppModule_ProvideMenuItemDaoFactory(dbProvider);
  }

  public static MenuItemDao provideMenuItemDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideMenuItemDao(db));
  }
}
