package com.extrotarget.extropos.printer.ui;

import com.extrotarget.extropos.printer.domain.usecase.PrintReceiptUseCase;
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
public final class PrinterViewModel_Factory implements Factory<PrinterViewModel> {
  private final Provider<PrintReceiptUseCase> printReceiptProvider;

  public PrinterViewModel_Factory(Provider<PrintReceiptUseCase> printReceiptProvider) {
    this.printReceiptProvider = printReceiptProvider;
  }

  @Override
  public PrinterViewModel get() {
    return newInstance(printReceiptProvider.get());
  }

  public static PrinterViewModel_Factory create(
      Provider<PrintReceiptUseCase> printReceiptProvider) {
    return new PrinterViewModel_Factory(printReceiptProvider);
  }

  public static PrinterViewModel newInstance(PrintReceiptUseCase printReceipt) {
    return new PrinterViewModel(printReceipt);
  }
}
