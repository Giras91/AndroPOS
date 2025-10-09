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
public final class AddCategoryUseCase_Factory implements Factory<AddCategoryUseCase> {
  private final Provider<IMenuRepository> menuRepositoryProvider;

  public AddCategoryUseCase_Factory(Provider<IMenuRepository> menuRepositoryProvider) {
    this.menuRepositoryProvider = menuRepositoryProvider;
  }

  @Override
  public AddCategoryUseCase get() {
    return newInstance(menuRepositoryProvider.get());
  }

  public static AddCategoryUseCase_Factory create(
      Provider<IMenuRepository> menuRepositoryProvider) {
    return new AddCategoryUseCase_Factory(menuRepositoryProvider);
  }

  public static AddCategoryUseCase newInstance(IMenuRepository menuRepository) {
    return new AddCategoryUseCase(menuRepository);
  }
}
