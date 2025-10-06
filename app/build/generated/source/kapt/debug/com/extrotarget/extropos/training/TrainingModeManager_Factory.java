package com.extrotarget.extropos.training;

import com.extrotarget.extropos.ui.main.DashboardPrefs;
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
public final class TrainingModeManager_Factory implements Factory<TrainingModeManager> {
  private final Provider<DashboardPrefs> prefsProvider;

  public TrainingModeManager_Factory(Provider<DashboardPrefs> prefsProvider) {
    this.prefsProvider = prefsProvider;
  }

  @Override
  public TrainingModeManager get() {
    return newInstance(prefsProvider.get());
  }

  public static TrainingModeManager_Factory create(Provider<DashboardPrefs> prefsProvider) {
    return new TrainingModeManager_Factory(prefsProvider);
  }

  public static TrainingModeManager newInstance(DashboardPrefs prefs) {
    return new TrainingModeManager(prefs);
  }
}
