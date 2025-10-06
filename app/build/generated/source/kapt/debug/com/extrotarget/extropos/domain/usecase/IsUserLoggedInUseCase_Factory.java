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
public final class IsUserLoggedInUseCase_Factory implements Factory<IsUserLoggedInUseCase> {
  private final Provider<IAuthRepository> authRepositoryProvider;

  public IsUserLoggedInUseCase_Factory(Provider<IAuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public IsUserLoggedInUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static IsUserLoggedInUseCase_Factory create(
      Provider<IAuthRepository> authRepositoryProvider) {
    return new IsUserLoggedInUseCase_Factory(authRepositoryProvider);
  }

  public static IsUserLoggedInUseCase newInstance(IAuthRepository authRepository) {
    return new IsUserLoggedInUseCase(authRepository);
  }
}
