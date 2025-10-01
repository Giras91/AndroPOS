package com.extrotarget.extropos.domain.usecase;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0086B\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/extrotarget/extropos/domain/usecase/AddItemToTicketUseCase;", "", "ticketRepository", "Lcom/extrotarget/extropos/data/repository/ITicketRepository;", "(Lcom/extrotarget/extropos/data/repository/ITicketRepository;)V", "invoke", "Lcom/extrotarget/extropos/domain/model/Ticket;", "ticket", "cartItem", "Lcom/extrotarget/extropos/domain/model/CartItem;", "(Lcom/extrotarget/extropos/domain/model/Ticket;Lcom/extrotarget/extropos/domain/model/CartItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@java.lang.Deprecated()
public final class AddItemToTicketUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.repository.ITicketRepository ticketRepository = null;
    
    @javax.inject.Inject()
    public AddItemToTicketUseCase(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.repository.ITicketRepository ticketRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.Ticket ticket, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.CartItem cartItem, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.Ticket> $completion) {
        return null;
    }
}