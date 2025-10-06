package com.extrotarget.extropos.ui.pin;

/**
 * Offline PIN login screen. Performs three roles:
 * 1. First-time setup: allow technician PIN (888888) once → AddUserActivity.
 * 2. After a user PIN is configured, validate it offline.
 * 3. Transition to MainActivity (existing) upon success.
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\r"}, d2 = {"Lcom/extrotarget/extropos/ui/pin/PinLoginActivity;", "Landroidx/activity/ComponentActivity;", "()V", "vm", "Lcom/extrotarget/extropos/ui/pin/PinLoginViewModel;", "getVm", "()Lcom/extrotarget/extropos/ui/pin/PinLoginViewModel;", "vm$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class PinLoginActivity extends androidx.activity.ComponentActivity {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy vm$delegate = null;
    
    public PinLoginActivity() {
        super();
    }
    
    private final com.extrotarget.extropos.ui.pin.PinLoginViewModel getVm() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
}