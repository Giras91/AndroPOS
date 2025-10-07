package com.extrotarget.extropos.domain.usecase;

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
public final class UpdateItemQuantityUseCase_Factory implements Factory<UpdateItemQuantityUseCase> {
  private final Provider<ITicketRepository> ticketRepositoryProvider;

  public UpdateItemQuantityUseCase_Factory(Provider<ITicketRepository> ticketRepositoryProvider) {
    this.ticketRepositoryProvider = ticketRepositoryProvider;
  }

  @Override
  public UpdateItemQuantityUseCase get() {
    return newInstance(ticketRepositoryProvider.get());
  }

  public static UpdateItemQuantityUseCase_Factory create(
      Provider<ITicketRepository> ticketRepositoryProvider) {
    return new UpdateItemQuantityUseCase_Factory(ticketRepositoryProvider);
  }

  public static UpdateItemQuantityUseCase newInstance(ITicketRepository ticketRepository) {
    return new UpdateItemQuantityUseCase(ticketRepository);
  }
}
