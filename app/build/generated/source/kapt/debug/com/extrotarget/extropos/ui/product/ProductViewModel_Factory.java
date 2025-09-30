package com.extrotarget.extropos.ui.product;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class ProductViewModel_Factory implements Factory<ProductViewModel> {
  @Override
  public ProductViewModel get() {
    return newInstance();
  }

  public static ProductViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ProductViewModel newInstance() {
    return new ProductViewModel();
  }

  private static final class InstanceHolder {
    private static final ProductViewModel_Factory INSTANCE = new ProductViewModel_Factory();
  }
}
