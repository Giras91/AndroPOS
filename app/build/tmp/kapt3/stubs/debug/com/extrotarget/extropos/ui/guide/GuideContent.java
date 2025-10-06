package com.extrotarget.extropos.ui.guide;

/**
 * Defines contextual hints and tutorial content for Guide Mode.
 * Each section of the app can have specific guidance messages.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0007"}, d2 = {"Lcom/extrotarget/extropos/ui/guide/GuideContent;", "", "()V", "Auth", "Dashboard", "Sales", "Training", "app_debug"})
public final class GuideContent {
    @org.jetbrains.annotations.NotNull()
    public static final com.extrotarget.extropos.ui.guide.GuideContent INSTANCE = null;
    
    private GuideContent() {
        super();
    }
    
    /**
     * Authentication flow guidance
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/extrotarget/extropos/ui/guide/GuideContent$Auth;", "", "()V", "EMAIL_LOGIN", "", "PIN_CREATION", "PIN_LOGIN", "TECHNICIAN_PIN", "app_debug"})
    public static final class Auth {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String EMAIL_LOGIN = "Enter your registered email address to begin the login process.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String PIN_CREATION = "Create a secure 4-6 digit PIN for quick access to your account.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String PIN_LOGIN = "Enter your PIN to access the POS system quickly and securely.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TECHNICIAN_PIN = "Use PIN 888888 for initial technician access to set up your first user account.";
        @org.jetbrains.annotations.NotNull()
        public static final com.extrotarget.extropos.ui.guide.GuideContent.Auth INSTANCE = null;
        
        private Auth() {
            super();
        }
    }
    
    /**
     * Dashboard guidance messages
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/extrotarget/extropos/ui/guide/GuideContent$Dashboard;", "", "()V", "CASH_REGISTER", "", "CUSTOMERS", "MODE_SWITCHES", "REPORTS", "SETTINGS", "TABLE_MANAGEMENT", "TECHNICIAN_NOTICE", "WELCOME", "app_debug"})
    public static final class Dashboard {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String WELCOME = "Welcome to ExtroPOS! This dashboard gives you quick access to all features. Toggle Guide Mode anytime for help.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String CASH_REGISTER = "Start sales transactions here. Process orders, payments, and print receipts.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TABLE_MANAGEMENT = "Manage restaurant tables, track occupancy, and assign orders to tables.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String REPORTS = "View sales reports, daily summaries, and business analytics.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String CUSTOMERS = "Manage customer information, loyalty programs, and purchase history.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SETTINGS = "Configure system settings, user preferences, and hardware connections.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String MODE_SWITCHES = "Use Training Mode to practice without affecting real data. Guide Mode shows helpful tips like this one.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TECHNICIAN_NOTICE = "You\'re logged in as Technician. Add a user account to enable full sales functionality.";
        @org.jetbrains.annotations.NotNull()
        public static final com.extrotarget.extropos.ui.guide.GuideContent.Dashboard INSTANCE = null;
        
        private Dashboard() {
            super();
        }
    }
    
    /**
     * Sales process guidance
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/extrotarget/extropos/ui/guide/GuideContent$Sales;", "", "()V", "ADD_ITEMS", "", "PAYMENT_PROCESS", "RECEIPT_PRINTING", "app_debug"})
    public static final class Sales {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String ADD_ITEMS = "Tap products to add them to the current order. Adjust quantities as needed.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String PAYMENT_PROCESS = "Select payment method and process the transaction when ready.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String RECEIPT_PRINTING = "Print receipts for customers and keep copies for your records.";
        @org.jetbrains.annotations.NotNull()
        public static final com.extrotarget.extropos.ui.guide.GuideContent.Sales INSTANCE = null;
        
        private Sales() {
            super();
        }
    }
    
    /**
     * Training mode specific guidance
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/extrotarget/extropos/ui/guide/GuideContent$Training;", "", "()V", "DATA_ISOLATION", "", "SAFE_PRACTICE", "TRAINING_ENABLED", "app_debug"})
    public static final class Training {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TRAINING_ENABLED = "Training Mode is ON. All data is isolated - practice freely without affecting real business data.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SAFE_PRACTICE = "Feel free to create test orders, try different payment methods, and explore features safely.";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String DATA_ISOLATION = "Training data is separate from your live business data and will be cleared when you exit Training Mode.";
        @org.jetbrains.annotations.NotNull()
        public static final com.extrotarget.extropos.ui.guide.GuideContent.Training INSTANCE = null;
        
        private Training() {
            super();
        }
    }
}