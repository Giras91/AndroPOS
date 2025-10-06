package com.extrotarget.extropos.data.local.auth;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class LocalAuthManager_Factory implements Factory<LocalAuthManager> {
  private final Provider<Context> contextProvider;

  public LocalAuthManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LocalAuthManager get() {
    return newInstance(contextProvider.get());
  }

  public static LocalAuthManager_Factory create(Provider<Context> contextProvider) {
    return new LocalAuthManager_Factory(contextProvider);
  }

  public static LocalAuthManager newInstance(Context context) {
    return new LocalAuthManager(context);
  }
}
