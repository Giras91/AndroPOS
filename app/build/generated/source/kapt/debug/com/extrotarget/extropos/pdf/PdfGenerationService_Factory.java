package com.extrotarget.extropos.pdf;

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
public final class PdfGenerationService_Factory implements Factory<PdfGenerationService> {
  private final Provider<Context> contextProvider;

  public PdfGenerationService_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PdfGenerationService get() {
    return newInstance(contextProvider.get());
  }

  public static PdfGenerationService_Factory create(Provider<Context> contextProvider) {
    return new PdfGenerationService_Factory(contextProvider);
  }

  public static PdfGenerationService newInstance(Context context) {
    return new PdfGenerationService(context);
  }
}
