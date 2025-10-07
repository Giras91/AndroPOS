package com.extrotarget.extropos.printer.hardware.vendor;

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
public final class DantSuEscPosAdapter_Factory implements Factory<DantSuEscPosAdapter> {
  private final Provider<String> hostProvider;

  private final Provider<Integer> portProvider;

  private final Provider<Integer> connectTimeoutMsProvider;

  public DantSuEscPosAdapter_Factory(Provider<String> hostProvider, Provider<Integer> portProvider,
      Provider<Integer> connectTimeoutMsProvider) {
    this.hostProvider = hostProvider;
    this.portProvider = portProvider;
    this.connectTimeoutMsProvider = connectTimeoutMsProvider;
  }

  @Override
  public DantSuEscPosAdapter get() {
    return newInstance(hostProvider.get(), portProvider.get(), connectTimeoutMsProvider.get());
  }

  public static DantSuEscPosAdapter_Factory create(Provider<String> hostProvider,
      Provider<Integer> portProvider, Provider<Integer> connectTimeoutMsProvider) {
    return new DantSuEscPosAdapter_Factory(hostProvider, portProvider, connectTimeoutMsProvider);
  }

  public static DantSuEscPosAdapter newInstance(String host, int port, int connectTimeoutMs) {
    return new DantSuEscPosAdapter(host, port, connectTimeoutMs);
  }
}
