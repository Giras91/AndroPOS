package com.extrotarget.extropos.data.repository

import android.util.Log
import com.extrotarget.extropos.data.remote.AppwriteService
import com.extrotarget.extropos.domain.model.*
import com.extrotarget.extropos.domain.repository.IAuthRepository
import io.appwrite.Client
import io.appwrite.ID
import io.appwrite.exceptions.AppwriteException
import io.appwrite.models.User as AppwriteUser
import io.appwrite.services.Account
import io.appwrite.services.Databases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val appwriteService: AppwriteService
) : IAuthRepository {

    companion object {
        private const val TAG = "AuthRepository"
        private const val DATABASE_ID = com.extrotarget.extropos.constants.AppwriteConfig.APPWRITE_DATABASE_ID
        private const val COLLECTION_ID = com.extrotarget.extropos.constants.AppwriteConfig.APPWRITE_USERS_COLLECTION_ID
    }

    override suspend fun login(request: LoginRequest): AuthResult = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Attempting login for email: ${request.email}")

            val account = Account(appwriteService.client)
            val session = account.createEmailPasswordSession(
                email = request.email,
                password = request.password
            )

            Log.d(TAG, "Login successful, session created: ${session.id}")

            // Get user details
            val appwriteUser = account.get()
            var user = getUserFromDatabase(appwriteUser.id)

            if (user == null) {
                // User is present in Appwrite Auth but missing in our database.
                // Attempt to create the profile document automatically. This
                // requires the collection to exist and permissions that allow
                // creation by the authenticated session (recommended).
                Log.w(TAG, "User authenticated but not found in database; creating profile document for id=${appwriteUser.id}")

                val createdUser = User(
                    id = appwriteUser.id,
                    email = appwriteUser.email ?: "",
                    name = appwriteUser.name ?: "",
                    companyName = "",
                    companyRegistrationNumber = "",
                    address = "",
                    phoneNumber = "",
                    emailVerified = appwriteUser.emailVerification ?: false
                )

                val saved = saveUserToDatabase(createdUser)
                if (!saved) {
                    Log.w(TAG, "Could not create user profile in database for id=${appwriteUser.id}; continuing without database record")
                }
                user = createdUser
                Log.d(TAG, "Continuing login with user from Auth service for id=${appwriteUser.id}")
            }

            AuthResult(
                success = true,
                user = user,
                requiresVerification = !user.emailVerified
            )

        } catch (e: AppwriteException) {
            Log.e(TAG, "Login failed", e)
            val errorMessage = when (e.code) {
                401 -> "Invalid email or password"
                429 -> "Too many login attempts. Please try again later"
                else -> "Login failed: ${e.message}"
            }
            AuthResult(success = false, errorMessage = errorMessage)
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error during login", e)
            AuthResult(success = false, errorMessage = "An unexpected error occurred")
        }
    }

    override suspend fun signup(request: SignupRequest): AuthResult = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Attempting signup for email: ${request.email}")

            val account = Account(appwriteService.client)

            // Create user account
            val appwriteUser = account.create(
                userId = ID.unique(),
                email = request.email,
                password = request.password,
                name = request.name
            )

            Log.d(TAG, "User account created: ${appwriteUser.id}")

            // Create user profile in database
            val user = User(
                id = appwriteUser.id,
                email = request.email,
                name = request.name,
                companyName = request.companyName,
                companyRegistrationNumber = request.companyRegistrationNumber,
                address = request.address,
                phoneNumber = request.phoneNumber,
                emailVerified = false
            )

            val saved = saveUserToDatabase(user)
            if (!saved) {
                Log.w(TAG, "Signup: could not save user to database, proceeding without DB record for id=${user.id}")
            }

            // Send verification email
            try {
                account.createVerification(url = com.extrotarget.extropos.constants.AppwriteConfig.APPWRITE_VERIFICATION_CALLBACK)
                Log.d(TAG, "Verification email sent")
            } catch (e: Exception) {
                Log.w(TAG, "Failed to send verification email", e)
                // Don't fail signup if email sending fails
            }

            AuthResult(
                success = true,
                user = user,
                requiresVerification = true
            )

        } catch (e: AppwriteException) {
            Log.e(TAG, "Signup failed", e)
            val errorMessage = when (e.code) {
                409 -> "An account with this email already exists"
                400 -> "Invalid email format or password too weak"
                else -> "Signup failed: ${e.message}"
            }
            AuthResult(success = false, errorMessage = errorMessage)
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error during signup", e)
            AuthResult(success = false, errorMessage = "An unexpected error occurred")
        }
    }

    override suspend fun logout(): Boolean = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Attempting logout")
            val account = Account(appwriteService.client)
            account.deleteSession("current")
            Log.d(TAG, "Logout successful")
            true
        } catch (e: Exception) {
            Log.e(TAG, "Logout failed", e)
            false
        }
    }

    override suspend fun getCurrentUser(): User? = withContext(Dispatchers.IO) {
        try {
            val account = Account(appwriteService.client)
            val appwriteUser = account.get()
            getUserFromDatabase(appwriteUser.id)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to get current user", e)
            null
        }
    }

    override suspend fun checkEmailVerification(): Boolean = withContext(Dispatchers.IO) {
        try {
            val user = getCurrentUser()
            user?.emailVerified ?: false
        } catch (e: Exception) {
            Log.e(TAG, "Failed to check email verification", e)
            false
        }
    }

    override suspend fun resendVerificationEmail(): Boolean = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "Resending verification email")
            val account = Account(appwriteService.client)
            account.createVerification(url = com.extrotarget.extropos.constants.AppwriteConfig.APPWRITE_VERIFICATION_CALLBACK)
            Log.d(TAG, "Verification email resent")
            true
        } catch (e: Exception) {
            Log.e(TAG, "Failed to resend verification email", e)
            false
        }
    }

    override suspend fun isUserLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        try {
            val account = Account(appwriteService.client)
            account.get()
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Attempts to save the user profile to the Appwrite database.
     * Returns true when the document was created successfully, false otherwise.
     * This method intentionally does not throw so callers can continue
     * authentication flows even when the database/collection is missing or
     * permissions are insufficient.
     */
    private suspend fun saveUserToDatabase(user: User): Boolean {
        return try {
            val databases = Databases(appwriteService.client)
            val documentData = mapOf(
                "email" to user.email,
                "name" to user.name,
                "companyName" to user.companyName,
                "companyRegistrationNumber" to user.companyRegistrationNumber,
                "address" to user.address,
                "phoneNumber" to user.phoneNumber,
                "emailVerified" to user.emailVerified,
                "createdAt" to user.createdAt,
                "updatedAt" to user.updatedAt
            )

            databases.createDocument(
                databaseId = DATABASE_ID,
                collectionId = COLLECTION_ID,
                documentId = user.id,
                data = documentData
            )

            Log.d(TAG, "User profile saved to database: ${user.id}")
            true

        } catch (e: Exception) {
            Log.e(TAG, "Failed to save user to database", e)
            false
        }
    }

    private suspend fun getUserFromDatabase(userId: String): User? {
        return try {
            val databases = Databases(appwriteService.client)
            val document = databases.getDocument(
                databaseId = DATABASE_ID,
                collectionId = COLLECTION_ID,
                documentId = userId
            )

            User(
                id = document.id,
                email = document.data["email"] as String,
                name = document.data["name"] as String,
                companyName = document.data["companyName"] as String,
                companyRegistrationNumber = document.data["companyRegistrationNumber"] as String,
                address = document.data["address"] as String,
                phoneNumber = document.data["phoneNumber"] as String,
                emailVerified = document.data["emailVerified"] as Boolean,
                createdAt = (document.data["createdAt"] as Number).toLong(),
                updatedAt = (document.data["updatedAt"] as Number).toLong()
            )
        } catch (e: Exception) {
            Log.e(TAG, "Failed to get user from database", e)
            null
        }
    }
}