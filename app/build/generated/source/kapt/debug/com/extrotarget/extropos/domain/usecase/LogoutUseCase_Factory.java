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
public final class LogoutUseCase_Factory implements Factory<LogoutUseCase> {
  private final Provider<IAuthRepository> authRepositoryProvider;

  public LogoutUseCase_Factory(Provider<IAuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public LogoutUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static LogoutUseCase_Factory create(Provider<IAuthRepository> authRepositoryProvider) {
    return new LogoutUseCase_Factory(authRepositoryProvider);
  }

  public static LogoutUseCase newInstance(IAuthRepository authRepository) {
    return new LogoutUseCase(authRepository);
  }
}
