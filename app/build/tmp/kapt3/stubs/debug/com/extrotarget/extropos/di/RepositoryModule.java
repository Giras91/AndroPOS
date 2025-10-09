package com.extrotarget.extropos.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\fH\'J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000fH\'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0012H\'J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0015H\'\u00a8\u0006\u0016"}, d2 = {"Lcom/extrotarget/extropos/di/RepositoryModule;", "", "()V", "bindAuthRepository", "Lcom/extrotarget/extropos/domain/repository/IAuthRepository;", "impl", "Lcom/extrotarget/extropos/data/repository/AuthRepository;", "bindMenuRepository", "Lcom/extrotarget/extropos/domain/repository/IMenuRepository;", "Lcom/extrotarget/extropos/data/repository/MenuRepository;", "bindOrderRepository", "Lcom/extrotarget/extropos/domain/repository/IOrderRepository;", "Lcom/extrotarget/extropos/data/repository/OrderRepository;", "bindProductRepository", "Lcom/extrotarget/extropos/domain/repository/IProductRepository;", "Lcom/extrotarget/extropos/data/repository/ProductRepositoryImpl;", "bindTableRepository", "Lcom/extrotarget/extropos/domain/repository/ITableRepository;", "Lcom/extrotarget/extropos/data/repository/TableRepository;", "bindTicketRepository", "Lcom/extrotarget/extropos/data/repository/ITicketRepository;", "Lcom/extrotarget/extropos/data/repository/TicketRepository;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.domain.repository.IAuthRepository bindAuthRepository(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.repository.AuthRepository impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.domain.repository.IMenuRepository bindMenuRepository(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.repository.MenuRepository impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.domain.repository.IProductRepository bindProductRepository(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.repository.ProductRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.domain.repository.IOrderRepository bindOrderRepository(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.repository.OrderRepository impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.domain.repository.ITableRepository bindTableRepository(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.repository.TableRepository impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.data.repository.ITicketRepository bindTicketRepository(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.repository.TicketRepository impl);
}