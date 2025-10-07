package com.extrotarget.extropos.ui.guide;

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
public final class GuideOverlayManager_Factory implements Factory<GuideOverlayManager> {
  private final Provider<DashboardPrefs> prefsProvider;

  public GuideOverlayManager_Factory(Provider<DashboardPrefs> prefsProvider) {
    this.prefsProvider = prefsProvider;
  }

  @Override
  public GuideOverlayManager get() {
    return newInstance(prefsProvider.get());
  }

  public static GuideOverlayManager_Factory create(Provider<DashboardPrefs> prefsProvider) {
    return new GuideOverlayManager_Factory(prefsProvider);
  }

  public static GuideOverlayManager newInstance(DashboardPrefs prefs) {
    return new GuideOverlayManager(prefs);
  }
}
