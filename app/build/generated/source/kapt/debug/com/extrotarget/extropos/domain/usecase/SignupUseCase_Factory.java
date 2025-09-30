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
public final class SignupUseCase_Factory implements Factory<SignupUseCase> {
  private final Provider<IAuthRepository> authRepositoryProvider;

  public SignupUseCase_Factory(Provider<IAuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public SignupUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static SignupUseCase_Factory create(Provider<IAuthRepository> authRepositoryProvider) {
    return new SignupUseCase_Factory(authRepositoryProvider);
  }

  public static SignupUseCase newInstance(IAuthRepository authRepository) {
    return new SignupUseCase(authRepository);
  }
}
