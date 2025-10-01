package com.extrotarget.extropos.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\n\u001a\u00020\u000bH\u00a6@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u00a6@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\rH\u00a6@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\rH\u00a6@\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0014H&J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u00a6@\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0017\u001a\u00020\u0018H\u00a6@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u001b\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\bJ\u001e\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0018H\u00a6@\u00a2\u0006\u0002\u0010\u001e\u00a8\u0006\u001f"}, d2 = {"Lcom/extrotarget/extropos/data/repository/ITicketRepository;", "", "addItemToTicket", "", "item", "Lcom/extrotarget/extropos/domain/model/TicketItem;", "(Lcom/extrotarget/extropos/domain/model/TicketItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearCurrentTicket", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeTicket", "createTicket", "Lcom/extrotarget/extropos/domain/model/Ticket;", "getAllDepartments", "", "Lcom/extrotarget/extropos/data/local/entity/DepartmentEntity;", "getAllTaxGroups", "Lcom/extrotarget/extropos/data/local/entity/TaxGroupEntity;", "getAllTenders", "Lcom/extrotarget/extropos/data/local/entity/TenderEntity;", "getCurrentTicket", "Lkotlinx/coroutines/flow/Flow;", "getSuspendedTickets", "getTicketById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeItemFromTicket", "suspendTicket", "updateItemQuantity", "newQuantity", "(Lcom/extrotarget/extropos/domain/model/TicketItem;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ITicketRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createTicket(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.Ticket> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.extrotarget.extropos.domain.model.Ticket> getCurrentTicket();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTicketById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.Ticket> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addItemToTicket(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.TicketItem item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateItemQuantity(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.TicketItem item, int newQuantity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeItemFromTicket(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.TicketItem item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearCurrentTicket(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object suspendTicket(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object completeTicket(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSuspendedTickets(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.Ticket>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllTenders(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.TenderEntity>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllDepartments(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.DepartmentEntity>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllTaxGroups(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.TaxGroupEntity>> $completion);
}