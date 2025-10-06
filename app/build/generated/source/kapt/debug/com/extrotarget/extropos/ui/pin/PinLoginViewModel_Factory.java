package com.extrotarget.extropos.ui.pin;

import com.extrotarget.extropos.data.local.auth.LocalAuthManager;
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
public final class PinLoginViewModel_Factory implements Factory<PinLoginViewModel> {
  private final Provider<LocalAuthManager> localAuthProvider;

  public PinLoginViewModel_Factory(Provider<LocalAuthManager> localAuthProvider) {
    this.localAuthProvider = localAuthProvider;
  }

  @Override
  public PinLoginViewModel get() {
    return newInstance(localAuthProvider.get());
  }

  public static PinLoginViewModel_Factory create(Provider<LocalAuthManager> localAuthProvider) {
    return new PinLoginViewModel_Factory(localAuthProvider);
  }

  public static PinLoginViewModel newInstance(LocalAuthManager localAuth) {
    return new PinLoginViewModel(localAuth);
  }
}
