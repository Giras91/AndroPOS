package com.extrotarget.extropos.auth;

/**
 * Handles Appwrite email login / signup and transitions to PIN setup/login.
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\r"}, d2 = {"Lcom/extrotarget/extropos/auth/EmailAuthActivity;", "Landroidx/activity/ComponentActivity;", "()V", "vm", "Lcom/extrotarget/extropos/auth/EmailAuthViewModel;", "getVm", "()Lcom/extrotarget/extropos/auth/EmailAuthViewModel;", "vm$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class EmailAuthActivity extends androidx.activity.ComponentActivity {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy vm$delegate = null;
    
    public EmailAuthActivity() {
        super();
    }
    
    private final com.extrotarget.extropos.auth.EmailAuthViewModel getVm() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
}