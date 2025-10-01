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

  private final Provider<GetMenuItemsUseCase> getMenuItemsUseCaseProvider;

  private final Provider<SearchMenuItemsUseCase> searchMenuItemsUseCaseProvider;

  public MenuViewModel_Factory(Provider<GetCategoriesUseCase> getCategoriesProvider,
      Provider<GetMenuItemsUseCase> getMenuItemsUseCaseProvider,
      Provider<SearchMenuItemsUseCase> searchMenuItemsUseCaseProvider) {
    this.getCategoriesProvider = getCategoriesProvider;
    this.getMenuItemsUseCaseProvider = getMenuItemsUseCaseProvider;
    this.searchMenuItemsUseCaseProvider = searchMenuItemsUseCaseProvider;
  }

  @Override
  public MenuViewModel get() {
    return newInstance(getCategoriesProvider.get(), getMenuItemsUseCaseProvider.get(), searchMenuItemsUseCaseProvider.get());
  }

  public static MenuViewModel_Factory create(Provider<GetCategoriesUseCase> getCategoriesProvider,
      Provider<GetMenuItemsUseCase> getMenuItemsUseCaseProvider,
      Provider<SearchMenuItemsUseCase> searchMenuItemsUseCaseProvider) {
    return new MenuViewModel_Factory(getCategoriesProvider, getMenuItemsUseCaseProvider, searchMenuItemsUseCaseProvider);
  }

  public static MenuViewModel newInstance(GetCategoriesUseCase getCategories,
      GetMenuItemsUseCase getMenuItemsUseCase, SearchMenuItemsUseCase searchMenuItemsUseCase) {
    return new MenuViewModel(getCategories, getMenuItemsUseCase, searchMenuItemsUseCase);
  }
}
