package com.extrotarget.extropos.domain.usecase;

import com.extrotarget.extropos.domain.repository.IAuthRepository;
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
public final class CheckAppActivationUseCase_Factory implements Factory<CheckAppActivationUseCase> {
  private final Provider<IAuthRepository> authRepositoryProvider;

  public CheckAppActivationUseCase_Factory(Provider<IAuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public CheckAppActivationUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static CheckAppActivationUseCase_Factory create(
      Provider<IAuthRepository> authRepositoryProvider) {
    return new CheckAppActivationUseCase_Factory(authRepositoryProvider);
  }

  public static CheckAppActivationUseCase newInstance(IAuthRepository authRepository) {
    return new CheckAppActivationUseCase(authRepository);
  }
}
