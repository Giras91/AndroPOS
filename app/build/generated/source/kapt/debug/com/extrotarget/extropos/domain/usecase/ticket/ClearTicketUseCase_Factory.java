package com.extrotarget.extropos.domain.usecase.ticket;

import com.extrotarget.extropos.data.repository.ITicketRepository;
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
public final class ClearTicketUseCase_Factory implements Factory<ClearTicketUseCase> {
  private final Provider<ITicketRepository> ticketRepositoryProvider;

  public ClearTicketUseCase_Factory(Provider<ITicketRepository> ticketRepositoryProvider) {
    this.ticketRepositoryProvider = ticketRepositoryProvider;
  }

  @Override
  public ClearTicketUseCase get() {
    return newInstance(ticketRepositoryProvider.get());
  }

  public static ClearTicketUseCase_Factory create(
      Provider<ITicketRepository> ticketRepositoryProvider) {
    return new ClearTicketUseCase_Factory(ticketRepositoryProvider);
  }

  public static ClearTicketUseCase newInstance(ITicketRepository ticketRepository) {
    return new ClearTicketUseCase(ticketRepository);
  }
}
