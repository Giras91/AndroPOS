package com.extrotarget.extropos.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\f\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\r\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0010H\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010H\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0010\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0017H\u0016J\u000e\u0010\u0018\u001a\u00020\u0019H\u0082@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010H\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001c\u001a\u00020\u0019H\u0096@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 H\u0082@\u00a2\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u000e\u0010#\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010$\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010%\u001a\u00020\u0019H\u0096@\u00a2\u0006\u0002\u0010&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/extrotarget/extropos/data/repository/TicketRepository;", "Lcom/extrotarget/extropos/data/repository/ITicketRepository;", "ticketDao", "Lcom/extrotarget/extropos/data/local/dao/TicketDao;", "(Lcom/extrotarget/extropos/data/local/dao/TicketDao;)V", "addItemToTicket", "", "item", "Lcom/extrotarget/extropos/domain/model/TicketItem;", "(Lcom/extrotarget/extropos/domain/model/TicketItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearCurrentTicket", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeTicket", "createTicket", "Lcom/extrotarget/extropos/domain/model/Ticket;", "getAllDepartments", "", "Lcom/extrotarget/extropos/data/local/entity/DepartmentEntity;", "getAllTaxGroups", "Lcom/extrotarget/extropos/data/local/entity/TaxGroupEntity;", "getAllTenders", "Lcom/extrotarget/extropos/data/local/entity/TenderEntity;", "getCurrentTicket", "Lkotlinx/coroutines/flow/Flow;", "getCurrentTicketId", "", "getSuspendedTickets", "getTicketById", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mapToDomain", "entity", "Lcom/extrotarget/extropos/data/local/entity/TicketEntity;", "(Lcom/extrotarget/extropos/data/local/entity/TicketEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeItemFromTicket", "suspendTicket", "updateItemQuantity", "newQuantity", "(Lcom/extrotarget/extropos/domain/model/TicketItem;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class TicketRepository implements com.extrotarget.extropos.data.repository.ITicketRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.dao.TicketDao ticketDao = null;
    
    @javax.inject.Inject()
    public TicketRepository(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.dao.TicketDao ticketDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object createTicket(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.Ticket> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.extrotarget.extropos.domain.model.Ticket> getCurrentTicket() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getTicketById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.Ticket> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addItemToTicket(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.TicketItem item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateItemQuantity(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.TicketItem item, int newQuantity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object removeItemFromTicket(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.TicketItem item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object clearCurrentTicket(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object suspendTicket(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object completeTicket(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getSuspendedTickets(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.Ticket>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAllTenders(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.TenderEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAllDepartments(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.DepartmentEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAllTaxGroups(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.TaxGroupEntity>> $completion) {
        return null;
    }
    
    private final java.lang.Object getCurrentTicketId(kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    private final java.lang.Object mapToDomain(com.extrotarget.extropos.data.local.entity.TicketEntity entity, kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.Ticket> $completion) {
        return null;
    }
}