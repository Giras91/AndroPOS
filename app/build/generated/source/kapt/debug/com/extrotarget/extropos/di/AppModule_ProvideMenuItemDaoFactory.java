package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.dao.MenuItemDao;
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
public final class AppModule_ProvideMenuItemDaoFactory implements Factory<MenuItemDao> {
  @Override
  public MenuItemDao get() {
    return provideMenuItemDao();
  }

  public static AppModule_ProvideMenuItemDaoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MenuItemDao provideMenuItemDao() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideMenuItemDao());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideMenuItemDaoFactory INSTANCE = new AppModule_ProvideMenuItemDaoFactory();
  }
}
