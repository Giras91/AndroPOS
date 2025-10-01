package com.extrotarget.extropos.di;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.appwrite.Client;
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
public final class AppModule_ProvideAppwriteClientFactory implements Factory<Client> {
  private final Provider<Application> appProvider;

  public AppModule_ProvideAppwriteClientFactory(Provider<Application> appProvider) {
    this.appProvider = appProvider;
  }

  @Override
  public Client get() {
    return provideAppwriteClient(appProvider.get());
  }

  public static AppModule_ProvideAppwriteClientFactory create(Provider<Application> appProvider) {
    return new AppModule_ProvideAppwriteClientFactory(appProvider);
  }

  public static Client provideAppwriteClient(Application app) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAppwriteClient(app));
  }
}
