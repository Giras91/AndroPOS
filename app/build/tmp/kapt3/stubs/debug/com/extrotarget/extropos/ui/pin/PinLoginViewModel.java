package com.extrotarget.extropos.ui.pin;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/extrotarget/extropos/ui/pin/PinLoginViewModel;", "Landroidx/lifecycle/ViewModel;", "localAuth", "Lcom/extrotarget/extropos/data/local/auth/LocalAuthManager;", "(Lcom/extrotarget/extropos/data/local/auth/LocalAuthManager;)V", "_events", "Lkotlinx/coroutines/channels/Channel;", "Lcom/extrotarget/extropos/ui/pin/PinLoginEvent;", "events", "Lkotlinx/coroutines/flow/Flow;", "getEvents", "()Lkotlinx/coroutines/flow/Flow;", "submitPin", "", "raw", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class PinLoginViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.data.local.auth.LocalAuthManager localAuth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<com.extrotarget.extropos.ui.pin.PinLoginEvent> _events = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.extrotarget.extropos.ui.pin.PinLoginEvent> events = null;
    
    @javax.inject.Inject()
    public PinLoginViewModel(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.auth.LocalAuthManager localAuth) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.extrotarget.extropos.ui.pin.PinLoginEvent> getEvents() {
        return null;
    }
    
    public final void submitPin(@org.jetbrains.annotations.NotNull()
    java.lang.String raw) {
    }
}