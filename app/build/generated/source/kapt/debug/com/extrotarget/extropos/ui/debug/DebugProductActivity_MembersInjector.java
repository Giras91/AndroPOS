package com.extrotarget.extropos.ui.debug;

import com.extrotarget.extropos.domain.repository.IProductRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DebugProductActivity_MembersInjector implements MembersInjector<DebugProductActivity> {
  private final Provider<IProductRepository> productRepositoryProvider;

  public DebugProductActivity_MembersInjector(
      Provider<IProductRepository> productRepositoryProvider) {
    this.productRepositoryProvider = productRepositoryProvider;
  }

  public static MembersInjector<DebugProductActivity> create(
      Provider<IProductRepository> productRepositoryProvider) {
    return new DebugProductActivity_MembersInjector(productRepositoryProvider);
  }

  @Override
  public void injectMembers(DebugProductActivity instance) {
    injectProductRepository(instance, productRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.extrotarget.extropos.ui.debug.DebugProductActivity.productRepository")
  public static void injectProductRepository(DebugProductActivity instance,
      IProductRepository productRepository) {
    instance.productRepository = productRepository;
  }
}
