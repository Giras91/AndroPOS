package com.extrotarget.extropos.domain.usecase;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0086B\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/extrotarget/extropos/domain/usecase/GetMenuItemsUseCase;", "", "menuRepository", "Lcom/extrotarget/extropos/domain/repository/IMenuRepository;", "(Lcom/extrotarget/extropos/domain/repository/IMenuRepository;)V", "invoke", "", "Lcom/extrotarget/extropos/domain/model/MenuItem;", "categoryId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class GetMenuItemsUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.repository.IMenuRepository menuRepository = null;
    
    @javax.inject.Inject()
    public GetMenuItemsUseCase(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.repository.IMenuRepository menuRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.Nullable()
    java.lang.String categoryId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.extrotarget.extropos.domain.model.MenuItem>> $completion) {
        return null;
    }
}