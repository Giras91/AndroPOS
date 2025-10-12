package com.extrotarget.extropos.ui.product;

import com.extrotarget.extropos.domain.repository.IProductRepository;
import com.extrotarget.extropos.domain.usecase.AddCategoryUseCase;
import com.extrotarget.extropos.domain.usecase.GetCategoriesUseCase;
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
public final class ProductViewModel_Factory implements Factory<ProductViewModel> {
  private final Provider<IProductRepository> productRepositoryProvider;

  private final Provider<AddCategoryUseCase> addCategoryUseCaseProvider;

  private final Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider;

  public ProductViewModel_Factory(Provider<IProductRepository> productRepositoryProvider,
      Provider<AddCategoryUseCase> addCategoryUseCaseProvider,
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider) {
    this.productRepositoryProvider = productRepositoryProvider;
    this.addCategoryUseCaseProvider = addCategoryUseCaseProvider;
    this.getCategoriesUseCaseProvider = getCategoriesUseCaseProvider;
  }

  @Override
  public ProductViewModel get() {
    return newInstance(productRepositoryProvider.get(), addCategoryUseCaseProvider.get(), getCategoriesUseCaseProvider.get());
  }

  public static ProductViewModel_Factory create(
      Provider<IProductRepository> productRepositoryProvider,
      Provider<AddCategoryUseCase> addCategoryUseCaseProvider,
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider) {
    return new ProductViewModel_Factory(productRepositoryProvider, addCategoryUseCaseProvider, getCategoriesUseCaseProvider);
  }

  public static ProductViewModel newInstance(IProductRepository productRepository,
      AddCategoryUseCase addCategoryUseCase, GetCategoriesUseCase getCategoriesUseCase) {
    return new ProductViewModel(productRepository, addCategoryUseCase, getCategoriesUseCase);
  }
}
