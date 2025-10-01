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
public final class GetCurrentTicketUseCase_Factory implements Factory<GetCurrentTicketUseCase> {
  private final Provider<ITicketRepository> ticketRepositoryProvider;

  public GetCurrentTicketUseCase_Factory(Provider<ITicketRepository> ticketRepositoryProvider) {
    this.ticketRepositoryProvider = ticketRepositoryProvider;
  }

  @Override
  public GetCurrentTicketUseCase get() {
    return newInstance(ticketRepositoryProvider.get());
  }

  public static GetCurrentTicketUseCase_Factory create(
      Provider<ITicketRepository> ticketRepositoryProvider) {
    return new GetCurrentTicketUseCase_Factory(ticketRepositoryProvider);
  }

  public static GetCurrentTicketUseCase newInstance(ITicketRepository ticketRepository) {
    return new GetCurrentTicketUseCase(ticketRepository);
  }
}
