package com.extrotarget.extropos.di;

import android.app.Application;
import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideContextFactory implements Factory<Context> {
  private final Provider<Application> applicationProvider;

  public AppModule_ProvideContextFactory(Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  @Override
  public Context get() {
    return provideContext(applicationProvider.get());
  }

  public static AppModule_ProvideContextFactory create(Provider<Application> applicationProvider) {
    return new AppModule_ProvideContextFactory(applicationProvider);
  }

  public static Context provideContext(Application application) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideContext(application));
  }
}
