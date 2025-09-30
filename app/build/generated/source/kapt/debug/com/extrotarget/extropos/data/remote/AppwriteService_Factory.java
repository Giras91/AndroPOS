package com.extrotarget.extropos.data.remote;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class AppwriteService_Factory implements Factory<AppwriteService> {
  @Override
  public AppwriteService get() {
    return newInstance();
  }

  public static AppwriteService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AppwriteService newInstance() {
    return new AppwriteService();
  }

  private static final class InstanceHolder {
    private static final AppwriteService_Factory INSTANCE = new AppwriteService_Factory();
  }
}
