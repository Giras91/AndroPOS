package com.extrotarget.extropos.data.sample;

import com.extrotarget.extropos.data.local.dao.CategoryDao;
import com.extrotarget.extropos.data.local.dao.MenuItemDao;
import com.extrotarget.extropos.data.local.dao.TableDao;
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
public final class SampleDataInserter_Factory implements Factory<SampleDataInserter> {
  private final Provider<CategoryDao> categoryDaoProvider;

  private final Provider<MenuItemDao> menuItemDaoProvider;

  private final Provider<TableDao> tableDaoProvider;

  private final Provider<Moshi> moshiProvider;

  public SampleDataInserter_Factory(Provider<CategoryDao> categoryDaoProvider,
      Provider<MenuItemDao> menuItemDaoProvider, Provider<TableDao> tableDaoProvider,
      Provider<Moshi> moshiProvider) {
    this.categoryDaoProvider = categoryDaoProvider;
    this.menuItemDaoProvider = menuItemDaoProvider;
    this.tableDaoProvider = tableDaoProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public SampleDataInserter get() {
    return newInstance(categoryDaoProvider.get(), menuItemDaoProvider.get(), tableDaoProvider.get(), moshiProvider.get());
  }

  public static SampleDataInserter_Factory create(Provider<CategoryDao> categoryDaoProvider,
      Provider<MenuItemDao> menuItemDaoProvider, Provider<TableDao> tableDaoProvider,
      Provider<Moshi> moshiProvider) {
    return new SampleDataInserter_Factory(categoryDaoProvider, menuItemDaoProvider, tableDaoProvider, moshiProvider);
  }

  public static SampleDataInserter newInstance(CategoryDao categoryDao, MenuItemDao menuItemDao,
      TableDao tableDao, Moshi moshi) {
    return new SampleDataInserter(categoryDao, menuItemDao, tableDao, moshi);
  }
}
