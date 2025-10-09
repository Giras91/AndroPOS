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
public final class AddMenuItemUseCase_Factory implements Factory<AddMenuItemUseCase> {
  private final Provider<IMenuRepository> menuRepositoryProvider;

  public AddMenuItemUseCase_Factory(Provider<IMenuRepository> menuRepositoryProvider) {
    this.menuRepositoryProvider = menuRepositoryProvider;
  }

  @Override
  public AddMenuItemUseCase get() {
    return newInstance(menuRepositoryProvider.get());
  }

  public static AddMenuItemUseCase_Factory create(
      Provider<IMenuRepository> menuRepositoryProvider) {
    return new AddMenuItemUseCase_Factory(menuRepositoryProvider);
  }

  public static AddMenuItemUseCase newInstance(IMenuRepository menuRepository) {
    return new AddMenuItemUseCase(menuRepository);
  }
}
