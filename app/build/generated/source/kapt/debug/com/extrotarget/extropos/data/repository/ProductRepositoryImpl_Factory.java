package com.extrotarget.extropos.data.repository;

import com.extrotarget.extropos.data.local.dao.ProductDao;
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
public final class ProductRepositoryImpl_Factory implements Factory<ProductRepositoryImpl> {
  private final Provider<ProductDao> productDaoProvider;

  public ProductRepositoryImpl_Factory(Provider<ProductDao> productDaoProvider) {
    this.productDaoProvider = productDaoProvider;
  }

  @Override
  public ProductRepositoryImpl get() {
    return newInstance(productDaoProvider.get());
  }

  public static ProductRepositoryImpl_Factory create(Provider<ProductDao> productDaoProvider) {
    return new ProductRepositoryImpl_Factory(productDaoProvider);
  }

  public static ProductRepositoryImpl newInstance(ProductDao productDao) {
    return new ProductRepositoryImpl(productDao);
  }
}
