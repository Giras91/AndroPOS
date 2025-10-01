package com.extrotarget.extropos.constants

object AppwriteConfig {
    const val APPWRITE_PROJECT_ID = "68da3df3003cad66fe0f"
    const val APPWRITE_PROJECT_NAME = "ExtroTarget"
    const val APPWRITE_PUBLIC_ENDPOINT = "https://syd.cloud.appwrite.io/v1"

    // Add your API key here (with health.read scope)
    const val APPWRITE_API_KEY = "standard_a8692a1e963b863f806d1bfa0d1ebddb575a154b88fe66236a01eee0d509f0122e4e8bc1c819becc62c286d32774feae6511054004663d30c91dddc7c7fcf27a555464dd661d43d9502e67797710b60706df63c1d9108c14210328686b6dd222198ab6e9496ab1d05f8e5e15c3c605f0909e585c7fed49d104a8fa3c9a32b4f0"

    // Configurable database/collection ids used by the mobile app. Update these
    // to match the IDs you created in the Appwrite console, or create the
    // database/collection with these exact ids. The project console shows the
    // database id as '68dcb6de0027c4ddb4d7' (visible in the UI), so we point
    // the app to that database by default now.
    const val APPWRITE_DATABASE_ID = "68dcb6de0027c4ddb4d7"
    const val APPWRITE_USERS_COLLECTION_ID = "users"
    // Absolute callback URL used for email verification links. This must be a
    // valid absolute URI (scheme + host). Update to your real app URL in
    // production. We default to the Appwrite endpoint + a path so it's a valid
    // absolute URL and won't be rejected by Appwrite's URI validation.
    const val APPWRITE_VERIFICATION_CALLBACK = "${APPWRITE_PUBLIC_ENDPOINT}/appwrite-callback-${APPWRITE_PROJECT_ID}"
}