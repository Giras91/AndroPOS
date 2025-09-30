package com.extrotarget.extropos.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0014\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0096@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u001e\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0096@\u00a2\u0006\u0002\u0010\u001bJ\f\u0010\u001c\u001a\u00020\b*\u00020\u001dH\u0002J\f\u0010\u001e\u001a\u00020\u001d*\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/extrotarget/extropos/data/repository/TableRepository;", "Lcom/extrotarget/extropos/domain/repository/ITableRepository;", "tableDao", "Lcom/extrotarget/extropos/data/local/dao/TableDao;", "(Lcom/extrotarget/extropos/data/local/dao/TableDao;)V", "createTable", "", "table", "Lcom/extrotarget/extropos/domain/model/Table;", "(Lcom/extrotarget/extropos/domain/model/Table;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTable", "", "tableId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTables", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAvailableTables", "getOccupiedTables", "getTableById", "id", "getTablesByStatus", "status", "Lcom/extrotarget/extropos/domain/model/TableStatus;", "(Lcom/extrotarget/extropos/domain/model/TableStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTable", "updateTableStatus", "(Ljava/lang/String;Lcom/extrotarget/extropos/domain/model/TableStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomain", "Lcom/extrotarget/extropos/data/local/entity/TableEntity;", "toEntity", "app_debug"})
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
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getTablesByStatus(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.TableStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.Table>> $completion) {
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
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object createTable(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.Table table, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateTable(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.Table table, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteTable(@org.jetbrains.annotations.NotNull()
    java.lang.String tableId, @org.jetbrains.annotations.NotNull()
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