package com.extrotarget.extropos.ui.cart;

import com.extrotarget.extropos.domain.usecase.ticket.AddItemToTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.ClearTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.CompleteTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.CreateTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.GetCurrentTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.RemoveItemFromTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.SuspendTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.UpdateItemQuantityUseCase;
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
public final class TicketViewModel_Factory implements Factory<TicketViewModel> {
  private final Provider<GetCurrentTicketUseCase> getCurrentTicketProvider;

  private final Provider<CreateTicketUseCase> createTicketProvider;

  private final Provider<AddItemToTicketUseCase> addItemToTicketProvider;

  private final Provider<UpdateItemQuantityUseCase> updateItemQuantityProvider;

  private final Provider<RemoveItemFromTicketUseCase> removeItemFromTicketProvider;

  private final Provider<ClearTicketUseCase> clearTicketProvider;

  private final Provider<SuspendTicketUseCase> suspendTicketProvider;

  private final Provider<CompleteTicketUseCase> completeTicketProvider;

  public TicketViewModel_Factory(Provider<GetCurrentTicketUseCase> getCurrentTicketProvider,
      Provider<CreateTicketUseCase> createTicketProvider,
      Provider<AddItemToTicketUseCase> addItemToTicketProvider,
      Provider<UpdateItemQuantityUseCase> updateItemQuantityProvider,
      Provider<RemoveItemFromTicketUseCase> removeItemFromTicketProvider,
      Provider<ClearTicketUseCase> clearTicketProvider,
      Provider<SuspendTicketUseCase> suspendTicketProvider,
      Provider<CompleteTicketUseCase> completeTicketProvider) {
    this.getCurrentTicketProvider = getCurrentTicketProvider;
    this.createTicketProvider = createTicketProvider;
    this.addItemToTicketProvider = addItemToTicketProvider;
    this.updateItemQuantityProvider = updateItemQuantityProvider;
    this.removeItemFromTicketProvider = removeItemFromTicketProvider;
    this.clearTicketProvider = clearTicketProvider;
    this.suspendTicketProvider = suspendTicketProvider;
    this.completeTicketProvider = completeTicketProvider;
  }

  @Override
  public TicketViewModel get() {
    return newInstance(getCurrentTicketProvider.get(), createTicketProvider.get(), addItemToTicketProvider.get(), updateItemQuantityProvider.get(), removeItemFromTicketProvider.get(), clearTicketProvider.get(), suspendTicketProvider.get(), completeTicketProvider.get());
  }

  public static TicketViewModel_Factory create(
      Provider<GetCurrentTicketUseCase> getCurrentTicketProvider,
      Provider<CreateTicketUseCase> createTicketProvider,
      Provider<AddItemToTicketUseCase> addItemToTicketProvider,
      Provider<UpdateItemQuantityUseCase> updateItemQuantityProvider,
      Provider<RemoveItemFromTicketUseCase> removeItemFromTicketProvider,
      Provider<ClearTicketUseCase> clearTicketProvider,
      Provider<SuspendTicketUseCase> suspendTicketProvider,
      Provider<CompleteTicketUseCase> completeTicketProvider) {
    return new TicketViewModel_Factory(getCurrentTicketProvider, createTicketProvider, addItemToTicketProvider, updateItemQuantityProvider, removeItemFromTicketProvider, clearTicketProvider, suspendTicketProvider, completeTicketProvider);
  }

  public static TicketViewModel newInstance(GetCurrentTicketUseCase getCurrentTicket,
      CreateTicketUseCase createTicket, AddItemToTicketUseCase addItemToTicket,
      UpdateItemQuantityUseCase updateItemQuantity,
      RemoveItemFromTicketUseCase removeItemFromTicket, ClearTicketUseCase clearTicket,
      SuspendTicketUseCase suspendTicket, CompleteTicketUseCase completeTicket) {
    return new TicketViewModel(getCurrentTicket, createTicket, addItemToTicket, updateItemQuantity, removeItemFromTicket, clearTicket, suspendTicket, completeTicket);
  }
}
