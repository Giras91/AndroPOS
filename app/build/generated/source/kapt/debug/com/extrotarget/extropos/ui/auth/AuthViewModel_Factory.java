package com.extrotarget.extropos.ui.auth;

import com.extrotarget.extropos.domain.usecase.CheckAppActivationUseCase;
import com.extrotarget.extropos.domain.usecase.LoginUseCase;
import com.extrotarget.extropos.domain.usecase.LogoutUseCase;
import com.extrotarget.extropos.domain.usecase.ResendVerificationEmailUseCase;
import com.extrotarget.extropos.domain.usecase.SignupUseCase;
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
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<LoginUseCase> loginUseCaseProvider;

  private final Provider<SignupUseCase> signupUseCaseProvider;

  private final Provider<LogoutUseCase> logoutUseCaseProvider;

  private final Provider<CheckAppActivationUseCase> checkAppActivationUseCaseProvider;

  private final Provider<ResendVerificationEmailUseCase> resendVerificationEmailUseCaseProvider;

  public AuthViewModel_Factory(Provider<LoginUseCase> loginUseCaseProvider,
      Provider<SignupUseCase> signupUseCaseProvider, Provider<LogoutUseCase> logoutUseCaseProvider,
      Provider<CheckAppActivationUseCase> checkAppActivationUseCaseProvider,
      Provider<ResendVerificationEmailUseCase> resendVerificationEmailUseCaseProvider) {
    this.loginUseCaseProvider = loginUseCaseProvider;
    this.signupUseCaseProvider = signupUseCaseProvider;
    this.logoutUseCaseProvider = logoutUseCaseProvider;
    this.checkAppActivationUseCaseProvider = checkAppActivationUseCaseProvider;
    this.resendVerificationEmailUseCaseProvider = resendVerificationEmailUseCaseProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(loginUseCaseProvider.get(), signupUseCaseProvider.get(), logoutUseCaseProvider.get(), checkAppActivationUseCaseProvider.get(), resendVerificationEmailUseCaseProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<LoginUseCase> loginUseCaseProvider,
      Provider<SignupUseCase> signupUseCaseProvider, Provider<LogoutUseCase> logoutUseCaseProvider,
      Provider<CheckAppActivationUseCase> checkAppActivationUseCaseProvider,
      Provider<ResendVerificationEmailUseCase> resendVerificationEmailUseCaseProvider) {
    return new AuthViewModel_Factory(loginUseCaseProvider, signupUseCaseProvider, logoutUseCaseProvider, checkAppActivationUseCaseProvider, resendVerificationEmailUseCaseProvider);
  }

  public static AuthViewModel newInstance(LoginUseCase loginUseCase, SignupUseCase signupUseCase,
      LogoutUseCase logoutUseCase, CheckAppActivationUseCase checkAppActivationUseCase,
      ResendVerificationEmailUseCase resendVerificationEmailUseCase) {
    return new AuthViewModel(loginUseCase, signupUseCase, logoutUseCase, checkAppActivationUseCase, resendVerificationEmailUseCase);
  }
}
