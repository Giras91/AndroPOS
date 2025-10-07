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
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class GetMenuItemsUseCase_Factory implements Factory<GetMenuItemsUseCase> {
  private final Provider<IMenuRepository> menuRepositoryProvider;

  public GetMenuItemsUseCase_Factory(Provider<IMenuRepository> menuRepositoryProvider) {
    this.menuRepositoryProvider = menuRepositoryProvider;
  }

  @Override
  public GetMenuItemsUseCase get() {
    return newInstance(menuRepositoryProvider.get());
  }

  public static GetMenuItemsUseCase_Factory create(
      Provider<IMenuRepository> menuRepositoryProvider) {
    return new GetMenuItemsUseCase_Factory(menuRepositoryProvider);
  }

  public static GetMenuItemsUseCase newInstance(IMenuRepository menuRepository) {
    return new GetMenuItemsUseCase(menuRepository);
  }
}
