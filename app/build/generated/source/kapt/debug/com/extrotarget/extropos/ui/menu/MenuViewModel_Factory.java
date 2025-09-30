package com.extrotarget.extropos.ui.menu;

import com.extrotarget.extropos.domain.usecase.GetCategoriesUseCase;
import com.extrotarget.extropos.domain.usecase.GetMenuItemsUseCase;
import com.extrotarget.extropos.domain.usecase.SearchMenuItemsUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
    "KotlinInternalInJava"
})
public final class MenuViewModel_Factory implements Factory<MenuViewModel> {
  private final Provider<GetCategoriesUseCase> getCategoriesProvider;

  private final Provider<GetMenuItemsUseCase> getMenuItemsProvider;

  private final Provider<SearchMenuItemsUseCase> searchMenuItemsProvider;

  public MenuViewModel_Factory(Provider<GetCategoriesUseCase> getCategoriesProvider,
      Provider<GetMenuItemsUseCase> getMenuItemsProvider,
      Provider<SearchMenuItemsUseCase> searchMenuItemsProvider) {
    this.getCategoriesProvider = getCategoriesProvider;
    this.getMenuItemsProvider = getMenuItemsProvider;
    this.searchMenuItemsProvider = searchMenuItemsProvider;
  }

  @Override
  public MenuViewModel get() {
    return newInstance(getCategoriesProvider.get(), getMenuItemsProvider.get(), searchMenuItemsProvider.get());
  }

  public static MenuViewModel_Factory create(Provider<GetCategoriesUseCase> getCategoriesProvider,
      Provider<GetMenuItemsUseCase> getMenuItemsProvider,
      Provider<SearchMenuItemsUseCase> searchMenuItemsProvider) {
    return new MenuViewModel_Factory(getCategoriesProvider, getMenuItemsProvider, searchMenuItemsProvider);
  }

  public static MenuViewModel newInstance(GetCategoriesUseCase getCategories,
      GetMenuItemsUseCase getMenuItems, SearchMenuItemsUseCase searchMenuItems) {
    return new MenuViewModel(getCategories, getMenuItems, searchMenuItems);
  }
}
