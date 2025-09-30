package com.example.pos.ui.login;

import com.example.pos.domain.usecase.AuthenticateUserUseCase;
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
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<AuthenticateUserUseCase> authenticateUserProvider;

  public LoginViewModel_Factory(Provider<AuthenticateUserUseCase> authenticateUserProvider) {
    this.authenticateUserProvider = authenticateUserProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(authenticateUserProvider.get());
  }

  public static LoginViewModel_Factory create(
      Provider<AuthenticateUserUseCase> authenticateUserProvider) {
    return new LoginViewModel_Factory(authenticateUserProvider);
  }

  public static LoginViewModel newInstance(AuthenticateUserUseCase authenticateUser) {
    return new LoginViewModel(authenticateUser);
  }
}
