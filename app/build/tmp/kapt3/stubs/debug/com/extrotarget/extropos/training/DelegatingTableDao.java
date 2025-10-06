package com.extrotarget.extropos.training;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0003H\u0002J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0015\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u001e\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\"\u0010\u001a\u001a\u00020\n2\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u001c\"\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/extrotarget/extropos/training/DelegatingTableDao;", "Lcom/extrotarget/extropos/data/local/dao/TableDao;", "live", "Lcom/extrotarget/extropos/data/local/InMemoryTableDao;", "training", "mode", "Lcom/extrotarget/extropos/training/TrainingModeManager;", "(Lcom/extrotarget/extropos/data/local/InMemoryTableDao;Lcom/extrotarget/extropos/data/local/InMemoryTableDao;Lcom/extrotarget/extropos/training/TrainingModeManager;)V", "active", "assignOrder", "", "tableId", "", "orderId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "Lcom/extrotarget/extropos/data/local/entity/TableEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAvailable", "getById", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOccupied", "updateStatus", "status", "upsert", "tables", "", "([Lcom/extrotarget/extropos/data/local/entity/TableEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class DelegatingTableDao implements com.extrotarget.extropos.data.local.dao.TableDao {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.InMemoryTableDao live = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.InMemoryTableDao training = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.training.TrainingModeManager mode = null;
    
    public DelegatingTableDao(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.InMemoryTableDao live, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.InMemoryTableDao training, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.training.TrainingModeManager mode) {
        super();
    }
    
    private final com.extrotarget.extropos.data.local.InMemoryTableDao active() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.TableEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.data.local.entity.TableEntity> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAvailable(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.TableEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getOccupied(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.TableEntity>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object upsert(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.TableEntity[] tables, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object assignOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String tableId, @org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}