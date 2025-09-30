package com.example.pos.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u0010\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u0012\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0016\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u0016\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u0016\u0010!\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ&\u0010\"\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%H\u00a7@\u00a2\u0006\u0002\u0010&J(\u0010\'\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010(\u001a\u00020\u001aH\u00a7@\u00a2\u0006\u0002\u0010)\u00a8\u0006*"}, d2 = {"Lcom/example/pos/data/local/dao/TicketDao;", "", "clearTicket", "", "ticketId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTicket", "id", "deleteTicketItem", "itemId", "getAllDepartments", "", "error/NonExistentClass", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTenders", "getCurrentTicket", "getCurrentTicketId", "getTicketById", "getTicketItems", "getTicketTenders", "getTicketsByState", "state", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTicket", "", "ticket", "(Lerror/NonExistentClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTicketItem", "item", "insertTicketTender", "tender", "updateTicket", "updateTicketItemQuantity", "quantity", "amount", "", "(IIDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTicketState", "updatedAt", "(ILjava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface TicketDao {
    
    @androidx.room.Query(value = "SELECT * FROM tickets WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTicketById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super error.NonExistentClass> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM tickets WHERE state = :state")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTicketsByState(@org.jetbrains.annotations.NotNull()
    java.lang.String state, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<error.NonExistentClass>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM ticket_items WHERE ticket_id = :ticketId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTicketItems(int ticketId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<error.NonExistentClass>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM ticket_tenders WHERE ticket_id = :ticketId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTicketTenders(int ticketId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<error.NonExistentClass>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTicket(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass ticket, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTicketItem(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTicketTender(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass tender, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTicket(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass ticket, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM tickets WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTicket(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM tenders")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllTenders(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<error.NonExistentClass>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM departments")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllDepartments(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<error.NonExistentClass>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM tickets WHERE state IN (\'SALE\', \'RETURN\') ORDER BY id DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCurrentTicket(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super error.NonExistentClass> $completion);
    
    @androidx.room.Query(value = "SELECT id FROM tickets WHERE state IN (\'SALE\', \'RETURN\') ORDER BY id DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCurrentTicketId(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "UPDATE tickets SET state = :state, updated_at = :updatedAt WHERE id = :ticketId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTicketState(int ticketId, @org.jetbrains.annotations.NotNull()
    java.lang.String state, long updatedAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM ticket_items WHERE ticket_id = :ticketId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearTicket(int ticketId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE ticket_items SET quantity = :quantity, amount = :amount WHERE id = :itemId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTicketItemQuantity(int itemId, int quantity, double amount, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM ticket_items WHERE id = :itemId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTicketItem(int itemId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}