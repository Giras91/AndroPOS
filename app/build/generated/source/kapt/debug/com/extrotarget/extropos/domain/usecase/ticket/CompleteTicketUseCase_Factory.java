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
    "KotlinInternalInJava"
})
public final class CompleteTicketUseCase_Factory implements Factory<CompleteTicketUseCase> {
  private final Provider<ITicketRepository> ticketRepositoryProvider;

  public CompleteTicketUseCase_Factory(Provider<ITicketRepository> ticketRepositoryProvider) {
    this.ticketRepositoryProvider = ticketRepositoryProvider;
  }

  @Override
  public CompleteTicketUseCase get() {
    return newInstance(ticketRepositoryProvider.get());
  }

  public static CompleteTicketUseCase_Factory create(
      Provider<ITicketRepository> ticketRepositoryProvider) {
    return new CompleteTicketUseCase_Factory(ticketRepositoryProvider);
  }

  public static CompleteTicketUseCase newInstance(ITicketRepository ticketRepository) {
    return new CompleteTicketUseCase(ticketRepository);
  }
}
