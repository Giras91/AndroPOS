package com.extrotarget.extropos.ui.cart;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001BG\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\u001eJ\u0006\u0010\"\u001a\u00020\u001eJ\b\u0010#\u001a\u00020\u001eH\u0002J\u000e\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020&J\u0006\u0010\'\u001a\u00020\u001eJ\u0016\u0010\b\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020&2\u0006\u0010(\u001a\u00020\u0017R\u0016\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/extrotarget/extropos/ui/cart/TicketViewModel;", "Landroidx/lifecycle/ViewModel;", "getCurrentTicket", "Lcom/extrotarget/extropos/domain/usecase/ticket/GetCurrentTicketUseCase;", "createTicket", "Lcom/extrotarget/extropos/domain/usecase/ticket/CreateTicketUseCase;", "addItemToTicket", "Lcom/extrotarget/extropos/domain/usecase/ticket/AddItemToTicketUseCase;", "updateItemQuantity", "Lcom/extrotarget/extropos/domain/usecase/ticket/UpdateItemQuantityUseCase;", "removeItemFromTicket", "Lcom/extrotarget/extropos/domain/usecase/ticket/RemoveItemFromTicketUseCase;", "clearTicket", "Lcom/extrotarget/extropos/domain/usecase/ticket/ClearTicketUseCase;", "suspendTicket", "Lcom/extrotarget/extropos/domain/usecase/ticket/SuspendTicketUseCase;", "completeTicket", "Lcom/extrotarget/extropos/domain/usecase/ticket/CompleteTicketUseCase;", "(Lcom/extrotarget/extropos/domain/usecase/ticket/GetCurrentTicketUseCase;Lcom/extrotarget/extropos/domain/usecase/ticket/CreateTicketUseCase;Lcom/extrotarget/extropos/domain/usecase/ticket/AddItemToTicketUseCase;Lcom/extrotarget/extropos/domain/usecase/ticket/UpdateItemQuantityUseCase;Lcom/extrotarget/extropos/domain/usecase/ticket/RemoveItemFromTicketUseCase;Lcom/extrotarget/extropos/domain/usecase/ticket/ClearTicketUseCase;Lcom/extrotarget/extropos/domain/usecase/ticket/SuspendTicketUseCase;Lcom/extrotarget/extropos/domain/usecase/ticket/CompleteTicketUseCase;)V", "_currentTicket", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/extrotarget/extropos/domain/model/Ticket;", "_itemCount", "", "currentTicket", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "itemCount", "getItemCount", "addItem", "", "cartItem", "Lcom/extrotarget/extropos/domain/model/CartItem;", "clearCurrentTicket", "completeCurrentTicket", "loadCurrentTicket", "removeItem", "item", "Lcom/extrotarget/extropos/domain/model/TicketItem;", "suspendCurrentTicket", "newQuantity", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TicketViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.ticket.GetCurrentTicketUseCase getCurrentTicket = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.ticket.CreateTicketUseCase createTicket = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.ticket.AddItemToTicketUseCase addItemToTicket = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.ticket.UpdateItemQuantityUseCase updateItemQuantity = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.ticket.RemoveItemFromTicketUseCase removeItemFromTicket = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.ticket.ClearTicketUseCase clearTicket = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.ticket.SuspendTicketUseCase suspendTicket = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.ticket.CompleteTicketUseCase completeTicket = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.extrotarget.extropos.domain.model.Ticket> _currentTicket = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.extrotarget.extropos.domain.model.Ticket> currentTicket = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _itemCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> itemCount = null;
    
    @javax.inject.Inject()
    public TicketViewModel(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.ticket.GetCurrentTicketUseCase getCurrentTicket, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.ticket.CreateTicketUseCase createTicket, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.ticket.AddItemToTicketUseCase addItemToTicket, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.ticket.UpdateItemQuantityUseCase updateItemQuantity, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.ticket.RemoveItemFromTicketUseCase removeItemFromTicket, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.ticket.ClearTicketUseCase clearTicket, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.ticket.SuspendTicketUseCase suspendTicket, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.ticket.CompleteTicketUseCase completeTicket) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.extrotarget.extropos.domain.model.Ticket> getCurrentTicket() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getItemCount() {
        return null;
    }
    
    private final void loadCurrentTicket() {
    }
    
    public final void addItem(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.CartItem cartItem) {
    }
    
    public final void updateItemQuantity(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.TicketItem item, int newQuantity) {
    }
    
    public final void removeItem(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.TicketItem item) {
    }
    
    public final void clearCurrentTicket() {
    }
    
    public final void suspendCurrentTicket() {
    }
    
    public final void completeCurrentTicket() {
    }
}