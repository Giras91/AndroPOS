package com.extrotarget.extropos.domain.usecase.ticket;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/extrotarget/extropos/domain/usecase/ticket/GetCurrentTicketUseCase;", "", "ticketRepository", "Lcom/extrotarget/extropos/data/repository/ITicketRepository;", "(Lcom/extrotarget/extropos/data/repository/ITicketRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/extrotarget/extropos/domain/model/Ticket;", "app_debug"})
public final class GetCurrentTicketUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.repository.ITicketRepository ticketRepository = null;
    
    @javax.inject.Inject()
    public GetCurrentTicketUseCase(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.repository.ITicketRepository ticketRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.extrotarget.extropos.domain.model.Ticket> invoke() {
        return null;
    }
}