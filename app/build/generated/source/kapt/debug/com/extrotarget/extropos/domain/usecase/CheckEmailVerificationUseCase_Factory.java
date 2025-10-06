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
public final class CheckEmailVerificationUseCase_Factory implements Factory<CheckEmailVerificationUseCase> {
  private final Provider<IAuthRepository> authRepositoryProvider;

  public CheckEmailVerificationUseCase_Factory(Provider<IAuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public CheckEmailVerificationUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static CheckEmailVerificationUseCase_Factory create(
      Provider<IAuthRepository> authRepositoryProvider) {
    return new CheckEmailVerificationUseCase_Factory(authRepositoryProvider);
  }

  public static CheckEmailVerificationUseCase newInstance(IAuthRepository authRepository) {
    return new CheckEmailVerificationUseCase(authRepository);
  }
}
