package com.extrotarget.extropos.ui.cart;

import com.extrotarget.extropos.domain.usecase.AddItemToTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ClearTicketUseCase;
import com.extrotarget.extropos.domain.usecase.CompleteTicketUseCase;
import com.extrotarget.extropos.domain.usecase.CreateTicketUseCase;
import com.extrotarget.extropos.domain.usecase.GetCurrentTicketUseCase;
import com.extrotarget.extropos.domain.usecase.RemoveItemFromTicketUseCase;
import com.extrotarget.extropos.domain.usecase.SuspendTicketUseCase;
import com.extrotarget.extropos.domain.usecase.UpdateItemQuantityUseCase;
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
public final class CartViewModel_Factory implements Factory<CartViewModel> {
  private final Provider<GetCurrentTicketUseCase> getCurrentTicketUseCaseProvider;

  private final Provider<CreateTicketUseCase> createTicketUseCaseProvider;

  private final Provider<AddItemToTicketUseCase> addItemToTicketUseCaseProvider;

  private final Provider<UpdateItemQuantityUseCase> updateItemQuantityUseCaseProvider;

  private final Provider<RemoveItemFromTicketUseCase> removeItemFromTicketUseCaseProvider;

  private final Provider<ClearTicketUseCase> clearTicketUseCaseProvider;

  private final Provider<SuspendTicketUseCase> suspendTicketUseCaseProvider;

  private final Provider<CompleteTicketUseCase> completeTicketUseCaseProvider;

  public CartViewModel_Factory(Provider<GetCurrentTicketUseCase> getCurrentTicketUseCaseProvider,
      Provider<CreateTicketUseCase> createTicketUseCaseProvider,
      Provider<AddItemToTicketUseCase> addItemToTicketUseCaseProvider,
      Provider<UpdateItemQuantityUseCase> updateItemQuantityUseCaseProvider,
      Provider<RemoveItemFromTicketUseCase> removeItemFromTicketUseCaseProvider,
      Provider<ClearTicketUseCase> clearTicketUseCaseProvider,
      Provider<SuspendTicketUseCase> suspendTicketUseCaseProvider,
      Provider<CompleteTicketUseCase> completeTicketUseCaseProvider) {
    this.getCurrentTicketUseCaseProvider = getCurrentTicketUseCaseProvider;
    this.createTicketUseCaseProvider = createTicketUseCaseProvider;
    this.addItemToTicketUseCaseProvider = addItemToTicketUseCaseProvider;
    this.updateItemQuantityUseCaseProvider = updateItemQuantityUseCaseProvider;
    this.removeItemFromTicketUseCaseProvider = removeItemFromTicketUseCaseProvider;
    this.clearTicketUseCaseProvider = clearTicketUseCaseProvider;
    this.suspendTicketUseCaseProvider = suspendTicketUseCaseProvider;
    this.completeTicketUseCaseProvider = completeTicketUseCaseProvider;
  }

  @Override
  public CartViewModel get() {
    return newInstance(getCurrentTicketUseCaseProvider.get(), createTicketUseCaseProvider.get(), addItemToTicketUseCaseProvider.get(), updateItemQuantityUseCaseProvider.get(), removeItemFromTicketUseCaseProvider.get(), clearTicketUseCaseProvider.get(), suspendTicketUseCaseProvider.get(), completeTicketUseCaseProvider.get());
  }

  public static CartViewModel_Factory create(
      Provider<GetCurrentTicketUseCase> getCurrentTicketUseCaseProvider,
      Provider<CreateTicketUseCase> createTicketUseCaseProvider,
      Provider<AddItemToTicketUseCase> addItemToTicketUseCaseProvider,
      Provider<UpdateItemQuantityUseCase> updateItemQuantityUseCaseProvider,
      Provider<RemoveItemFromTicketUseCase> removeItemFromTicketUseCaseProvider,
      Provider<ClearTicketUseCase> clearTicketUseCaseProvider,
      Provider<SuspendTicketUseCase> suspendTicketUseCaseProvider,
      Provider<CompleteTicketUseCase> completeTicketUseCaseProvider) {
    return new CartViewModel_Factory(getCurrentTicketUseCaseProvider, createTicketUseCaseProvider, addItemToTicketUseCaseProvider, updateItemQuantityUseCaseProvider, removeItemFromTicketUseCaseProvider, clearTicketUseCaseProvider, suspendTicketUseCaseProvider, completeTicketUseCaseProvider);
  }

  public static CartViewModel newInstance(GetCurrentTicketUseCase getCurrentTicketUseCase,
      CreateTicketUseCase createTicketUseCase, AddItemToTicketUseCase addItemToTicketUseCase,
      UpdateItemQuantityUseCase updateItemQuantityUseCase,
      RemoveItemFromTicketUseCase removeItemFromTicketUseCase,
      ClearTicketUseCase clearTicketUseCase, SuspendTicketUseCase suspendTicketUseCase,
      CompleteTicketUseCase completeTicketUseCase) {
    return new CartViewModel(getCurrentTicketUseCase, createTicketUseCase, addItemToTicketUseCase, updateItemQuantityUseCase, removeItemFromTicketUseCase, clearTicketUseCase, suspendTicketUseCase, completeTicketUseCase);
  }
}
