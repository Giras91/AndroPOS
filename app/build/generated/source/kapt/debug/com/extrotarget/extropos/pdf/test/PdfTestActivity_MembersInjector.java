package com.extrotarget.extropos.pdf.test;

import com.extrotarget.extropos.pdf.PdfGenerationService;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class PdfTestActivity_MembersInjector implements MembersInjector<PdfTestActivity> {
  private final Provider<PdfGenerationService> pdfServiceProvider;

  public PdfTestActivity_MembersInjector(Provider<PdfGenerationService> pdfServiceProvider) {
    this.pdfServiceProvider = pdfServiceProvider;
  }

  public static MembersInjector<PdfTestActivity> create(
      Provider<PdfGenerationService> pdfServiceProvider) {
    return new PdfTestActivity_MembersInjector(pdfServiceProvider);
  }

  @Override
  public void injectMembers(PdfTestActivity instance) {
    injectPdfService(instance, pdfServiceProvider.get());
  }

  @InjectedFieldSignature("com.extrotarget.extropos.pdf.test.PdfTestActivity.pdfService")
  public static void injectPdfService(PdfTestActivity instance, PdfGenerationService pdfService) {
    instance.pdfService = pdfService;
  }
}
