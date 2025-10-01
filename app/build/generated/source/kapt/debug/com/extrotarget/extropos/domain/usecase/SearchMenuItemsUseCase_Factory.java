package com.extrotarget.extropos.domain.usecase;

import com.extrotarget.extropos.domain.repository.IMenuRepository;
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
public final class SearchMenuItemsUseCase_Factory implements Factory<SearchMenuItemsUseCase> {
  private final Provider<IMenuRepository> menuRepositoryProvider;

  public SearchMenuItemsUseCase_Factory(Provider<IMenuRepository> menuRepositoryProvider) {
    this.menuRepositoryProvider = menuRepositoryProvider;
  }

  @Override
  public SearchMenuItemsUseCase get() {
    return newInstance(menuRepositoryProvider.get());
  }

  public static SearchMenuItemsUseCase_Factory create(
      Provider<IMenuRepository> menuRepositoryProvider) {
    return new SearchMenuItemsUseCase_Factory(menuRepositoryProvider);
  }

  public static SearchMenuItemsUseCase newInstance(IMenuRepository menuRepository) {
    return new SearchMenuItemsUseCase(menuRepository);
  }
}
