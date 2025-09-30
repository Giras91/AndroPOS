package com.example.pos.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\f\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\r\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000fH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u000fH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u000fH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0016J\u000e\u0010\u0014\u001a\u00020\u0015H\u0082@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u000fH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0015H\u0096@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\bH\u0082@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\u001d\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u0015H\u0096@\u00a2\u0006\u0002\u0010 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/pos/data/repository/TicketRepository;", "Lcom/example/pos/data/repository/ITicketRepository;", "ticketDao", "Lcom/example/pos/data/local/dao/TicketDao;", "(Lcom/example/pos/data/local/dao/TicketDao;)V", "addItemToTicket", "", "item", "error/NonExistentClass", "(Lerror/NonExistentClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearCurrentTicket", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeTicket", "createTicket", "getAllDepartments", "", "getAllTaxGroups", "getAllTenders", "getCurrentTicket", "Lkotlinx/coroutines/flow/Flow;", "getCurrentTicketId", "", "getSuspendedTickets", "getTicketById", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mapToDomain", "entity", "removeItemFromTicket", "suspendTicket", "updateItemQuantity", "newQuantity", "(Lerror/NonExistentClass;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class TicketRepository implements com.example.pos.data.repository.ITicketRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.pos.data.local.dao.TicketDao ticketDao = null;
    
    @javax.inject.Inject()
    public TicketRepository(@org.jetbrains.annotations.NotNull()
    com.example.pos.data.local.dao.TicketDao ticketDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object createTicket(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super error.NonExistentClass> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<error.NonExistentClass> getCurrentTicket() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getTicketById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super error.NonExistentClass> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addItemToTicket(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateItemQuantity(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass item, int newQuantity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object removeItemFromTicket(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass item, @org.jetbrains.annotations.NotNull()
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
    kotlin.coroutines.Continuation<? super java.util.List<error.NonExistentClass>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAllTenders(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<error.NonExistentClass>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAllDepartments(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<error.NonExistentClass>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAllTaxGroups(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<error.NonExistentClass>> $completion) {
        return null;
    }
    
    private final java.lang.Object getCurrentTicketId(kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    private final java.lang.Object mapToDomain(error.NonExistentClass entity, kotlin.coroutines.Continuation<? super error.NonExistentClass> $completion) {
        return null;
    }
}