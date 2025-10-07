package com.extrotarget.extropos.printer.domain.usecase;

import com.extrotarget.extropos.printer.data.IPrinterRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class PrintReceiptUseCase_Factory implements Factory<PrintReceiptUseCase> {
  private final Provider<IPrinterRepository> repoProvider;

  public PrintReceiptUseCase_Factory(Provider<IPrinterRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public PrintReceiptUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static PrintReceiptUseCase_Factory create(Provider<IPrinterRepository> repoProvider) {
    return new PrintReceiptUseCase_Factory(repoProvider);
  }

  public static PrintReceiptUseCase newInstance(IPrinterRepository repo) {
    return new PrintReceiptUseCase(repo);
  }
}
