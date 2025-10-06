package com.extrotarget.extropos.data.repository;

import com.extrotarget.extropos.data.local.dao.CategoryDao;
import com.extrotarget.extropos.data.local.dao.MenuItemDao;
import com.squareup.moshi.Moshi;
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
public final class MenuRepository_Factory implements Factory<MenuRepository> {
  private final Provider<CategoryDao> categoryDaoProvider;

  private final Provider<MenuItemDao> menuItemDaoProvider;

  private final Provider<Moshi> moshiProvider;

  public MenuRepository_Factory(Provider<CategoryDao> categoryDaoProvider,
      Provider<MenuItemDao> menuItemDaoProvider, Provider<Moshi> moshiProvider) {
    this.categoryDaoProvider = categoryDaoProvider;
    this.menuItemDaoProvider = menuItemDaoProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public MenuRepository get() {
    return newInstance(categoryDaoProvider.get(), menuItemDaoProvider.get(), moshiProvider.get());
  }

  public static MenuRepository_Factory create(Provider<CategoryDao> categoryDaoProvider,
      Provider<MenuItemDao> menuItemDaoProvider, Provider<Moshi> moshiProvider) {
    return new MenuRepository_Factory(categoryDaoProvider, menuItemDaoProvider, moshiProvider);
  }

  public static MenuRepository newInstance(CategoryDao categoryDao, MenuItemDao menuItemDao,
      Moshi moshi) {
    return new MenuRepository(categoryDao, menuItemDao, moshi);
  }
}
