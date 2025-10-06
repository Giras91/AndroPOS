package com.extrotarget.extropos.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0016"}, d2 = {"Lcom/extrotarget/extropos/auth/EmailAuthViewModel;", "Landroidx/lifecycle/ViewModel;", "login", "Lcom/extrotarget/extropos/domain/usecase/LoginUseCase;", "isLoggedIn", "Lcom/extrotarget/extropos/domain/usecase/IsUserLoggedInUseCase;", "checkEmailVerification", "Lcom/extrotarget/extropos/domain/usecase/CheckEmailVerificationUseCase;", "(Lcom/extrotarget/extropos/domain/usecase/LoginUseCase;Lcom/extrotarget/extropos/domain/usecase/IsUserLoggedInUseCase;Lcom/extrotarget/extropos/domain/usecase/CheckEmailVerificationUseCase;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/extrotarget/extropos/auth/EmailAuthState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "bootstrap", "", "performLogin", "email", "", "password", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class EmailAuthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.LoginUseCase login = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.IsUserLoggedInUseCase isLoggedIn = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.CheckEmailVerificationUseCase checkEmailVerification = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.extrotarget.extropos.auth.EmailAuthState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.extrotarget.extropos.auth.EmailAuthState> state = null;
    
    @javax.inject.Inject()
    public EmailAuthViewModel(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.LoginUseCase login, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.IsUserLoggedInUseCase isLoggedIn, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.CheckEmailVerificationUseCase checkEmailVerification) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.extrotarget.extropos.auth.EmailAuthState> getState() {
        return null;
    }
    
    public final void bootstrap() {
    }
    
    public final void performLogin(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
}