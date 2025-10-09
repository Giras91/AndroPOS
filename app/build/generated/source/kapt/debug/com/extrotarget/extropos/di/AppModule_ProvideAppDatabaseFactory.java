package com.extrotarget.extropos.di;

import android.app.Application;
import com.extrotarget.extropos.data.local.AppDatabase;
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
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class AppModule_ProvideAppDatabaseFactory implements Factory<AppDatabase> {
  private final Provider<Application> applicationProvider;

  public AppModule_ProvideAppDatabaseFactory(Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  @Override
  public AppDatabase get() {
    return provideAppDatabase(applicationProvider.get());
  }

  public static AppModule_ProvideAppDatabaseFactory create(
      Provider<Application> applicationProvider) {
    return new AppModule_ProvideAppDatabaseFactory(applicationProvider);
  }

  public static AppDatabase provideAppDatabase(Application application) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAppDatabase(application));
  }
}
