package com.extrotarget.extropos.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0096@\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u0010J\f\u0010\u0011\u001a\u00020\u0007*\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/extrotarget/extropos/data/repository/ProductRepositoryImpl;", "Lcom/extrotarget/extropos/domain/repository/IProductRepository;", "productDao", "Lcom/extrotarget/extropos/data/local/dao/ProductDao;", "(Lcom/extrotarget/extropos/data/local/dao/ProductDao;)V", "getAllProducts", "", "Lcom/extrotarget/extropos/domain/model/Product;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductById", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertProduct", "", "product", "(Lcom/extrotarget/extropos/domain/model/Product;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomain", "Lcom/extrotarget/extropos/data/local/entity/ProductEntity;", "app_debug"})
public final class ProductRepositoryImpl implements com.extrotarget.extropos.domain.repository.IProductRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.dao.ProductDao productDao = null;
    
    @javax.inject.Inject()
    public ProductRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.dao.ProductDao productDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAllProducts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.Product>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getProductById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.extrotarget.extropos.domain.model.Product> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object upsertProduct(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.Product product, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final com.extrotarget.extropos.domain.model.Product toDomain(com.extrotarget.extropos.data.local.entity.ProductEntity $this$toDomain) {
        return null;
    }
}