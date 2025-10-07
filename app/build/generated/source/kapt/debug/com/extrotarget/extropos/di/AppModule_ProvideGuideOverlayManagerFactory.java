package com.extrotarget.extropos.di;

import com.extrotarget.extropos.ui.guide.GuideOverlayManager;
import com.extrotarget.extropos.ui.main.DashboardPrefs;
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
public final class AppModule_ProvideGuideOverlayManagerFactory implements Factory<GuideOverlayManager> {
  private final Provider<DashboardPrefs> prefsProvider;

  public AppModule_ProvideGuideOverlayManagerFactory(Provider<DashboardPrefs> prefsProvider) {
    this.prefsProvider = prefsProvider;
  }

  @Override
  public GuideOverlayManager get() {
    return provideGuideOverlayManager(prefsProvider.get());
  }

  public static AppModule_ProvideGuideOverlayManagerFactory create(
      Provider<DashboardPrefs> prefsProvider) {
    return new AppModule_ProvideGuideOverlayManagerFactory(prefsProvider);
  }

  public static GuideOverlayManager provideGuideOverlayManager(DashboardPrefs prefs) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideGuideOverlayManager(prefs));
  }
}
