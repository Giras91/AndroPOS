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
    "KotlinInternalInJava"
})
public final class ResendVerificationEmailUseCase_Factory implements Factory<ResendVerificationEmailUseCase> {
  private final Provider<IAuthRepository> authRepositoryProvider;

  public ResendVerificationEmailUseCase_Factory(Provider<IAuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public ResendVerificationEmailUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static ResendVerificationEmailUseCase_Factory create(
      Provider<IAuthRepository> authRepositoryProvider) {
    return new ResendVerificationEmailUseCase_Factory(authRepositoryProvider);
  }

  public static ResendVerificationEmailUseCase newInstance(IAuthRepository authRepository) {
    return new ResendVerificationEmailUseCase(authRepository);
  }
}
