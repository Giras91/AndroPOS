package com.extrotarget.extropos.data.local.dao;

/**
 * DAO for CustomerEntity - customer management.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0015H\'J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0017\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0019"}, d2 = {"Lcom/extrotarget/extropos/data/local/dao/CustomerDao;", "", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deactivate", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllActive", "", "Lcom/extrotarget/extropos/data/local/entity/CustomerEntity;", "getByEmail", "email", "getById", "getByPhone", "phone", "insert", "customer", "(Lcom/extrotarget/extropos/data/local/entity/CustomerEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAllActive", "Lkotlinx/coroutines/flow/Flow;", "search", "query", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface CustomerDao {
    
    @androidx.room.Query(value = "SELECT * FROM customers WHERE isActive = 1 ORDER BY name ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllActive(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.CustomerEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM customers WHERE isActive = 1 ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.extrotarget.extropos.data.local.entity.CustomerEntity>> observeAllActive();
    
    @androidx.room.Query(value = "SELECT * FROM customers WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.data.local.entity.CustomerEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM customers WHERE phone = :phone LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByPhone(@org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.data.local.entity.CustomerEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM customers WHERE email = :email LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.data.local.entity.CustomerEntity> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.CustomerEntity customer, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.entity.CustomerEntity customer, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE customers SET isActive = 0 WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deactivate(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM customers")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM customers WHERE name LIKE \'%\' || :query || \'%\' OR phone LIKE \'%\' || :query || \'%\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object search(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.data.local.entity.CustomerEntity>> $completion);
}