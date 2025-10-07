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
public final class GetCategoriesUseCase_Factory implements Factory<GetCategoriesUseCase> {
  private final Provider<IMenuRepository> menuRepositoryProvider;

  public GetCategoriesUseCase_Factory(Provider<IMenuRepository> menuRepositoryProvider) {
    this.menuRepositoryProvider = menuRepositoryProvider;
  }

  @Override
  public GetCategoriesUseCase get() {
    return newInstance(menuRepositoryProvider.get());
  }

  public static GetCategoriesUseCase_Factory create(
      Provider<IMenuRepository> menuRepositoryProvider) {
    return new GetCategoriesUseCase_Factory(menuRepositoryProvider);
  }

  public static GetCategoriesUseCase newInstance(IMenuRepository menuRepository) {
    return new GetCategoriesUseCase(menuRepository);
  }
}
