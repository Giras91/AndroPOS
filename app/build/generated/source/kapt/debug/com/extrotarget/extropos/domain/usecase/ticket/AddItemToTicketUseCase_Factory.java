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
public final class AddItemToTicketUseCase_Factory implements Factory<AddItemToTicketUseCase> {
  private final Provider<ITicketRepository> ticketRepositoryProvider;

  public AddItemToTicketUseCase_Factory(Provider<ITicketRepository> ticketRepositoryProvider) {
    this.ticketRepositoryProvider = ticketRepositoryProvider;
  }

  @Override
  public AddItemToTicketUseCase get() {
    return newInstance(ticketRepositoryProvider.get());
  }

  public static AddItemToTicketUseCase_Factory create(
      Provider<ITicketRepository> ticketRepositoryProvider) {
    return new AddItemToTicketUseCase_Factory(ticketRepositoryProvider);
  }

  public static AddItemToTicketUseCase newInstance(ITicketRepository ticketRepository) {
    return new AddItemToTicketUseCase(ticketRepository);
  }
}
