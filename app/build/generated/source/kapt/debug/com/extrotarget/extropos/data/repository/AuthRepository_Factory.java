package com.extrotarget.extropos.data.repository;

import com.extrotarget.extropos.data.remote.AppwriteService;
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
    "KotlinInternalInJava"
})
public final class AuthRepository_Factory implements Factory<AuthRepository> {
  private final Provider<AppwriteService> appwriteServiceProvider;

  public AuthRepository_Factory(Provider<AppwriteService> appwriteServiceProvider) {
    this.appwriteServiceProvider = appwriteServiceProvider;
  }

  @Override
  public AuthRepository get() {
    return newInstance(appwriteServiceProvider.get());
  }

  public static AuthRepository_Factory create(Provider<AppwriteService> appwriteServiceProvider) {
    return new AuthRepository_Factory(appwriteServiceProvider);
  }

  public static AuthRepository newInstance(AppwriteService appwriteService) {
    return new AuthRepository(appwriteService);
  }
}
