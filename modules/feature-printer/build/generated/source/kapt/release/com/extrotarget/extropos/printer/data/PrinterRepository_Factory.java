package com.extrotarget.extropos.printer.data;

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
public final class PrinterRepository_Factory implements Factory<PrinterRepository> {
  @Override
  public PrinterRepository get() {
    return newInstance();
  }

  public static PrinterRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PrinterRepository newInstance() {
    return new PrinterRepository();
  }

  private static final class InstanceHolder {
    private static final PrinterRepository_Factory INSTANCE = new PrinterRepository_Factory();
  }
}
