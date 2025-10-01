#!/usr/bin/env bash

# create_appwrite_resources.sh
# Idempotent helper to create a database + users collection + attributes in Appwrite.
# Run locally (requires an admin API key). Do NOT commit your API key.

set -euo pipefail

# CONFIG (can be provided via env)
ENDPOINT="${APPWRITE_ENDPOINT:-https://syd.cloud.appwrite.io/v1}"
PROJECT_ID="${APPWRITE_PROJECT_ID:-68da3df3003cad66fe0f}"
API_KEY="${APPWRITE_API_KEY:-}" # must be set in env or exported before running
DATABASE_ID="${APPWRITE_DATABASE_ID:-main}"
COLLECTION_ID="${APPWRITE_USERS_COLLECTION_ID:-users}"

if [[ -z "$API_KEY" ]]; then
  echo "ERROR: APPWRITE_API_KEY not set. Export APPWRITE_API_KEY in your shell and re-run." >&2
  exit 2
fi

CURL_OPTS=( -s -H "X-Appwrite-Project: $PROJECT_ID" -H "X-Appwrite-Key: $API_KEY" -H "Content-Type: application/json" )

http_ok() {
  local code="$1"
  [[ "$code" -ge 200 && "$code" -lt 300 ]]
}

exists_database() {
  local code
  code=$(curl -o /dev/null -w "%{http_code}" -X GET "${ENDPOINT}/databases/${DATABASE_ID}" "${CURL_OPTS[@]}")
  http_ok "$code"
}

create_database() {
  echo "Creating database '$DATABASE_ID'..."
  local resp code
  resp=$(curl -s -w "\n%{http_code}" -X POST "${ENDPOINT}/databases" "${CURL_OPTS[@]}" -d "{\"databaseId\":\"${DATABASE_ID}\",\"name\":\"${DATABASE_ID}\"}")
  code=$(printf '%s' "$resp" | tail -n1)
  if http_ok "$code"; then
    echo "Database created or already exists (status $code)."
  else
    echo "Failed to create database: HTTP $code" >&2
    printf '%s' "$resp" | sed -n '1p' >&2
    exit 3
  fi
}

exists_collection() {
  local code
  code=$(curl -o /dev/null -w "%{http_code}" -X GET "${ENDPOINT}/databases/${DATABASE_ID}/collections/${COLLECTION_ID}" "${CURL_OPTS[@]}")
  http_ok "$code"
}

create_collection() {
  echo "Creating collection '$COLLECTION_ID' in database '$DATABASE_ID'..."
  local resp code
  resp=$(curl -s -w "\n%{http_code}" -X POST "${ENDPOINT}/databases/${DATABASE_ID}/collections" "${CURL_OPTS[@]}" -d "{\"collectionId\":\"${COLLECTION_ID}\",\"name\":\"${COLLECTION_ID}\",\"read\":[\"role:all\"],\"write\":[\"role:all\"],\"attributes\":[]}")
  code=$(printf '%s' "$resp" | tail -n1)
  if http_ok "$code"; then
    echo "Collection created (status $code)."
  else
    echo "Failed to create collection: HTTP $code" >&2
    printf '%s' "$resp" | sed -n '1p' >&2
    exit 4
  fi
}

create_attribute_string() {
  local key="$1" required="$2"
  echo "Adding string attribute '$key' (required=$required)..."
  local resp code
  # Appwrite requires a 'size' for string attributes; default to 255
  resp=$(curl -s -w "\n%{http_code}" -X POST "${ENDPOINT}/databases/${DATABASE_ID}/collections/${COLLECTION_ID}/attributes/string" "${CURL_OPTS[@]}" -d "{\"key\":\"${key}\",\"required\":${required},\"size\":255}")
  code=$(printf '%s' "$resp" | tail -n1)
  if http_ok "$code"; then
    echo "Attribute $key created (status $code)."
  else
    if [[ "$code" -eq 409 ]]; then
      echo "Attribute $key already exists."
    else
      echo "Failed to create attribute $key: HTTP $code" >&2
      printf '%s' "$resp" | sed -n '1p' >&2
      exit 5
    fi
  fi
}

create_attribute_boolean() {
  local key="$1" required="$2"
  echo "Adding boolean attribute '$key' (required=$required)..."
  local resp code
  resp=$(curl -s -w "\n%{http_code}" -X POST "${ENDPOINT}/databases/${DATABASE_ID}/collections/${COLLECTION_ID}/attributes/boolean" "${CURL_OPTS[@]}" -d "{\"key\":\"${key}\",\"required\":${required}}")
  code=$(printf '%s' "$resp" | tail -n1)
  if http_ok "$code"; then
    echo "Attribute $key created (status $code)."
  else
    if [[ "$code" -eq 409 ]]; then
      echo "Attribute $key already exists."
    else
      echo "Failed to create attribute $key: HTTP $code" >&2
      printf '%s' "$resp" | sed -n '1p' >&2
      exit 6
    fi
  fi
}

create_attribute_integer() {
  local key="$1" required="$2" min_val="$3" max_val="$4"
  echo "Adding integer attribute '$key' (required=$required min=$min_val max=$max_val)..."
  local resp code
  resp=$(curl -s -w "\n%{http_code}" -X POST "${ENDPOINT}/databases/${DATABASE_ID}/collections/${COLLECTION_ID}/attributes/integer" "${CURL_OPTS[@]}" -d "{\"key\":\"${key}\",\"required\":${required},\"min\":${min_val},\"max\":${max_val}}")
  code=$(printf '%s' "$resp" | tail -n1)
  if http_ok "$code"; then
    echo "Attribute $key created (status $code)."
  else
    if [[ "$code" -eq 409 ]]; then
      echo "Attribute $key already exists."
    else
      echo "Failed to create attribute $key: HTTP $code" >&2
      printf '%s' "$resp" | sed -n '1p' >&2
      exit 7
    fi
  fi
}

main() {
  echo "Appwrite resource creator - endpoint=$ENDPOINT project=$PROJECT_ID db=$DATABASE_ID collection=$COLLECTION_ID"

  if ! exists_database; then
    create_database
  else
    echo "Database '$DATABASE_ID' already exists."
  fi

  if ! exists_collection; then
    create_collection
  else
    echo "Collection '$COLLECTION_ID' already exists."
  fi

  # Create attributes used by the app. These calls are idempotent; if attribute exists Appwrite returns 409.
  create_attribute_string "email" true
  create_attribute_string "name" false
  create_attribute_string "companyName" false
  create_attribute_string "companyRegistrationNumber" false
  create_attribute_string "address" false
  create_attribute_string "phoneNumber" false
  create_attribute_boolean "emailVerified" false
  # createdAt / updatedAt as integer (64-bit)
  create_attribute_integer "createdAt" false 0 9223372036854775807
  create_attribute_integer "updatedAt" false 0 9223372036854775807

  echo "Done."
}

main "$@"