package com.extrotarget.extropos.data.remote;

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
public final class AppwriteService_Factory implements Factory<AppwriteService> {
  private final Provider<Context> contextProvider;

  public AppwriteService_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AppwriteService get() {
    return newInstance(contextProvider.get());
  }

  public static AppwriteService_Factory create(Provider<Context> contextProvider) {
    return new AppwriteService_Factory(contextProvider);
  }

  public static AppwriteService newInstance(Context context) {
    return new AppwriteService(context);
  }
}
