package com.extrotarget.extropos.ui.guide

/**
 * Defines contextual hints and tutorial content for Guide Mode.
 * Each section of the app can have specific guidance messages.
 */
object GuideContent {
    
    /**
     * Dashboard guidance messages
     */
    object Dashboard {
        const val WELCOME = "Welcome to ExtroPOS! This dashboard gives you quick access to all features. Toggle Guide Mode anytime for help."
        const val CASH_REGISTER = "Start sales transactions here. Process orders, payments, and print receipts."
        const val TABLE_MANAGEMENT = "Manage restaurant tables, track occupancy, and assign orders to tables."
        const val REPORTS = "View sales reports, daily summaries, and business analytics."
        const val CUSTOMERS = "Manage customer information, loyalty programs, and purchase history."
        const val SETTINGS = "Configure system settings, user preferences, and hardware connections."
        const val MODE_SWITCHES = "Use Training Mode to practice without affecting real data. Guide Mode shows helpful tips like this one."
        const val TECHNICIAN_NOTICE = "You're logged in as Technician. Add a user account to enable full sales functionality."
    }
    
    /**
     * Training mode specific guidance
     */
    object Training {
        const val TRAINING_ENABLED = "Training Mode is ON. All data is isolated - practice freely without affecting real business data."
        const val SAFE_PRACTICE = "Feel free to create test orders, try different payment methods, and explore features safely."
        const val DATA_ISOLATION = "Training data is separate from your live business data and will be cleared when you exit Training Mode."
    }
    
    /**
     * Authentication flow guidance
     */
    object Auth {
        const val EMAIL_LOGIN = "Enter your registered email address to begin the login process."
        const val PIN_CREATION = "Create a secure 4-6 digit PIN for quick access to your account."
        const val PIN_LOGIN = "Enter your PIN to access the POS system quickly and securely."
        const val TECHNICIAN_PIN = "Use PIN 888888 for initial technician access to set up your first user account."
    }
    
    /**
     * Sales process guidance
     */
    object Sales {
        const val ADD_ITEMS = "Tap products to add them to the current order. Adjust quantities as needed."
        const val PAYMENT_PROCESS = "Select payment method and process the transaction when ready."
        const val RECEIPT_PRINTING = "Print receipts for customers and keep copies for your records."
    }
}