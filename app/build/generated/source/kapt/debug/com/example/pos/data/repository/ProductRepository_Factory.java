package com.example.pos.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import error.NonExistentClass;
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
    "KotlinInternalInJava"
})
public final class ProductRepository_Factory implements Factory<ProductRepository> {
  private final Provider<NonExistentClass> daoProvider;

  public ProductRepository_Factory(Provider<NonExistentClass> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public ProductRepository get() {
    return newInstance(daoProvider.get());
  }

  public static ProductRepository_Factory create(Provider<NonExistentClass> daoProvider) {
    return new ProductRepository_Factory(daoProvider);
  }

  public static ProductRepository newInstance(NonExistentClass dao) {
    return new ProductRepository(dao);
  }
}
