package com.extrotarget.extropos.domain.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class UpdateTableStatusUseCase_Factory implements Factory<UpdateTableStatusUseCase> {
  @Override
  public UpdateTableStatusUseCase get() {
    return newInstance();
  }

  public static UpdateTableStatusUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static UpdateTableStatusUseCase newInstance() {
    return new UpdateTableStatusUseCase();
  }

  private static final class InstanceHolder {
    private static final UpdateTableStatusUseCase_Factory INSTANCE = new UpdateTableStatusUseCase_Factory();
  }
}
