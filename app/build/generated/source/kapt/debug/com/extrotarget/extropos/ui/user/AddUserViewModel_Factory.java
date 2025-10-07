package com.extrotarget.extropos.ui.user;

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
public final class AddUserViewModel_Factory implements Factory<AddUserViewModel> {
  private final Provider<LocalAuthManager> localAuthProvider;

  public AddUserViewModel_Factory(Provider<LocalAuthManager> localAuthProvider) {
    this.localAuthProvider = localAuthProvider;
  }

  @Override
  public AddUserViewModel get() {
    return newInstance(localAuthProvider.get());
  }

  public static AddUserViewModel_Factory create(Provider<LocalAuthManager> localAuthProvider) {
    return new AddUserViewModel_Factory(localAuthProvider);
  }

  public static AddUserViewModel newInstance(LocalAuthManager localAuth) {
    return new AddUserViewModel(localAuth);
  }
}
