package com.extrotarget.extropos.domain.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/extrotarget/extropos/domain/model/AuthState;", "", "(Ljava/lang/String;I)V", "NOT_AUTHENTICATED", "AUTHENTICATED_NOT_VERIFIED", "AUTHENTICATED_VERIFIED", "LOADING", "app_debug"})
public enum AuthState {
    /*public static final*/ NOT_AUTHENTICATED /* = new NOT_AUTHENTICATED() */,
    /*public static final*/ AUTHENTICATED_NOT_VERIFIED /* = new AUTHENTICATED_NOT_VERIFIED() */,
    /*public static final*/ AUTHENTICATED_VERIFIED /* = new AUTHENTICATED_VERIFIED() */,
    /*public static final*/ LOADING /* = new LOADING() */;
    
    AuthState() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.extrotarget.extropos.domain.model.AuthState> getEntries() {
        return null;
    }
}