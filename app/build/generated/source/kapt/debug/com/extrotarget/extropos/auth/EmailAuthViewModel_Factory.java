package com.extrotarget.extropos.auth;

import com.extrotarget.extropos.domain.usecase.CheckEmailVerificationUseCase;
import com.extrotarget.extropos.domain.usecase.IsUserLoggedInUseCase;
import com.extrotarget.extropos.domain.usecase.LoginUseCase;
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
public final class EmailAuthViewModel_Factory implements Factory<EmailAuthViewModel> {
  private final Provider<LoginUseCase> loginProvider;

  private final Provider<IsUserLoggedInUseCase> isLoggedInProvider;

  private final Provider<CheckEmailVerificationUseCase> checkEmailVerificationProvider;

  public EmailAuthViewModel_Factory(Provider<LoginUseCase> loginProvider,
      Provider<IsUserLoggedInUseCase> isLoggedInProvider,
      Provider<CheckEmailVerificationUseCase> checkEmailVerificationProvider) {
    this.loginProvider = loginProvider;
    this.isLoggedInProvider = isLoggedInProvider;
    this.checkEmailVerificationProvider = checkEmailVerificationProvider;
  }

  @Override
  public EmailAuthViewModel get() {
    return newInstance(loginProvider.get(), isLoggedInProvider.get(), checkEmailVerificationProvider.get());
  }

  public static EmailAuthViewModel_Factory create(Provider<LoginUseCase> loginProvider,
      Provider<IsUserLoggedInUseCase> isLoggedInProvider,
      Provider<CheckEmailVerificationUseCase> checkEmailVerificationProvider) {
    return new EmailAuthViewModel_Factory(loginProvider, isLoggedInProvider, checkEmailVerificationProvider);
  }

  public static EmailAuthViewModel newInstance(LoginUseCase login, IsUserLoggedInUseCase isLoggedIn,
      CheckEmailVerificationUseCase checkEmailVerification) {
    return new EmailAuthViewModel(login, isLoggedIn, checkEmailVerification);
  }
}
