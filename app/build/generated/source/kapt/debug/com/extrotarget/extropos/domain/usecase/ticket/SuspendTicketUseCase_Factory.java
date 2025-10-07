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
public final class SuspendTicketUseCase_Factory implements Factory<SuspendTicketUseCase> {
  private final Provider<ITicketRepository> ticketRepositoryProvider;

  public SuspendTicketUseCase_Factory(Provider<ITicketRepository> ticketRepositoryProvider) {
    this.ticketRepositoryProvider = ticketRepositoryProvider;
  }

  @Override
  public SuspendTicketUseCase get() {
    return newInstance(ticketRepositoryProvider.get());
  }

  public static SuspendTicketUseCase_Factory create(
      Provider<ITicketRepository> ticketRepositoryProvider) {
    return new SuspendTicketUseCase_Factory(ticketRepositoryProvider);
  }

  public static SuspendTicketUseCase newInstance(ITicketRepository ticketRepository) {
    return new SuspendTicketUseCase(ticketRepository);
  }
}
