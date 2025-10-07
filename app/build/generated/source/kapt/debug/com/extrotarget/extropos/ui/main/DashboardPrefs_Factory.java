package com.extrotarget.extropos.ui.main;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class DashboardPrefs_Factory implements Factory<DashboardPrefs> {
  private final Provider<Context> contextProvider;

  public DashboardPrefs_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public DashboardPrefs get() {
    return newInstance(contextProvider.get());
  }

  public static DashboardPrefs_Factory create(Provider<Context> contextProvider) {
    return new DashboardPrefs_Factory(contextProvider);
  }

  public static DashboardPrefs newInstance(Context context) {
    return new DashboardPrefs(context);
  }
}
