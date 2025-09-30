package com.example.pos.domain.usecase;

import com.example.pos.data.repository.IAuthRepository;
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
public final class AuthenticateUserUseCase_Factory implements Factory<AuthenticateUserUseCase> {
  private final Provider<IAuthRepository> authRepositoryProvider;

  public AuthenticateUserUseCase_Factory(Provider<IAuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public AuthenticateUserUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static AuthenticateUserUseCase_Factory create(
      Provider<IAuthRepository> authRepositoryProvider) {
    return new AuthenticateUserUseCase_Factory(authRepositoryProvider);
  }

  public static AuthenticateUserUseCase newInstance(IAuthRepository authRepository) {
    return new AuthenticateUserUseCase(authRepository);
  }
}
