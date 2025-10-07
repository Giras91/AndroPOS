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
public final class CreateTicketUseCase_Factory implements Factory<CreateTicketUseCase> {
  private final Provider<ITicketRepository> ticketRepositoryProvider;

  public CreateTicketUseCase_Factory(Provider<ITicketRepository> ticketRepositoryProvider) {
    this.ticketRepositoryProvider = ticketRepositoryProvider;
  }

  @Override
  public CreateTicketUseCase get() {
    return newInstance(ticketRepositoryProvider.get());
  }

  public static CreateTicketUseCase_Factory create(
      Provider<ITicketRepository> ticketRepositoryProvider) {
    return new CreateTicketUseCase_Factory(ticketRepositoryProvider);
  }

  public static CreateTicketUseCase newInstance(ITicketRepository ticketRepository) {
    return new CreateTicketUseCase(ticketRepository);
  }
}
