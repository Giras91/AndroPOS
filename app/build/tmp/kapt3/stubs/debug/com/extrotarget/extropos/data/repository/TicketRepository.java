package com.extrotarget.extropos.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u000e\u0010\u0011\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0014H\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0014H\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u001bH\u0016J\u000e\u0010\u001c\u001a\u00020\u001dH\u0082@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014H\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00122\u0006\u0010 \u001a\u00020\u001dH\u0096@\u00a2\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001dH\u0002J\u0016\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\'H\u0082@\u00a2\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0010\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020#H\u0002J\u000e\u0010,\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u001e\u0010-\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010.\u001a\u00020\u001dH\u0096@\u00a2\u0006\u0002\u0010/R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/extrotarget/extropos/data/repository/TicketRepository;", "Lcom/extrotarget/extropos/data/repository/ITicketRepository;", "ticketDao", "Lcom/extrotarget/extropos/data/local/dao/TicketDao;", "(Lcom/extrotarget/extropos/data/local/dao/TicketDao;)V", "addItemToTicket", "", "item", "Lcom/extrotarget/extropos/domain/model/TicketItem;", "(Lcom/extrotarget/extropos/domain/model/TicketItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addTicketTender", "tender", "Lcom/extrotarget/extropos/domain/model/TicketTender;", "(Lcom/extrotarget/extropos/domain/model/TicketTender;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearCurrentTicket", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeTicket", "createTicket", "Lcom/extrotarget/extropos/domain/model/Ticket;", "getAllDepartments", "", "Lcom/extrotarget/extropos/data/local/entity/DepartmentEntity;", "getAllTaxGroups", "Lcom/extrotarget/extropos/data/local/entity/TaxGroupEntity;", "getAllTenders", "Lcom/extrotarget/extropos/data/local/entity/TenderEntity;", "getCurrentTicket", "Lkotlinx/coroutines/flow/Flow;", "getCurrentTicketId", "", "getSuspendedTickets", "getTicketById", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "intToStatus", "Lcom/extrotarget/extropos/domain/model/TicketStatus;", "state", "mapToDomain", "entity", "Lcom/extrotarget/extropos/data/local/entity/TicketEntity;", "(Lcom/extrotarget/extropos/data/local/entity/TicketEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeItemFromTicket", "statusToInt", "status", "suspendTicket", "updateItemQuantity", "newQuantity", "(Lcom/extrotarget/extropos/domain/model/TicketItem;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
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
    public java.lang.Object addTicketTender(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.TicketTender tender, @org.jetbrains.annotations.NotNull()
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
    
    private final com.extrotarget.extropos.domain.model.TicketStatus intToStatus(int state) {
        return null;
    }
    
    private final int statusToInt(com.extrotarget.extropos.domain.model.TicketStatus status) {
        return 0;
    }
}