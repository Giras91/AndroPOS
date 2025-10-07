package com.extrotarget.extropos.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u0011\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0012\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u0013J\u001e\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0096@\u00a2\u0006\u0002\u0010\u0017J\f\u0010\u0018\u001a\u00020\r*\u00020\u0019H\u0002J\f\u0010\u001a\u001a\u00020\u0019*\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/extrotarget/extropos/data/repository/TableRepository;", "Lcom/extrotarget/extropos/domain/repository/ITableRepository;", "tableDao", "Lcom/extrotarget/extropos/data/local/dao/TableDao;", "(Lcom/extrotarget/extropos/data/local/dao/TableDao;)V", "assignOrderToTable", "", "tableId", "", "orderId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTables", "", "Lcom/extrotarget/extropos/domain/model/Table;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAvailableTables", "getOccupiedTables", "getTableById", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTableStatus", "status", "Lcom/extrotarget/extropos/domain/model/TableStatus;", "(Ljava/lang/String;Lcom/extrotarget/extropos/domain/model/TableStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomain", "Lcom/extrotarget/extropos/data/local/entity/TableEntity;", "toEntity", "app_debug"})
public final class TableRepository implements com.extrotarget.extropos.domain.repository.ITableRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.dao.TableDao tableDao = null;
    
    @javax.inject.Inject()
    public TableRepository(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.dao.TableDao tableDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAllTables(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.Table>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getTableById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.Table> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateTableStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String tableId, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.TableStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object assignOrderToTable(@org.jetbrains.annotations.NotNull()
    java.lang.String tableId, @org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAvailableTables(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.Table>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getOccupiedTables(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.Table>> $completion) {
        return null;
    }
    
    private final com.extrotarget.extropos.domain.model.Table toDomain(com.extrotarget.extropos.data.local.entity.TableEntity $this$toDomain) {
        return null;
    }
    
    private final com.extrotarget.extropos.data.local.entity.TableEntity toEntity(com.extrotarget.extropos.domain.model.Table $this$toEntity) {
        return null;
    }
}