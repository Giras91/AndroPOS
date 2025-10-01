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
public final class LoginUseCase_Factory implements Factory<LoginUseCase> {
  private final Provider<IAuthRepository> authRepositoryProvider;

  public LoginUseCase_Factory(Provider<IAuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public LoginUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static LoginUseCase_Factory create(Provider<IAuthRepository> authRepositoryProvider) {
    return new LoginUseCase_Factory(authRepositoryProvider);
  }

  public static LoginUseCase newInstance(IAuthRepository authRepository) {
    return new LoginUseCase(authRepository);
  }
}
