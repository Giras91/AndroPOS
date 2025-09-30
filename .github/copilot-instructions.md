# AndroPOS — Copilot instructions (concise)
# AndroPOS — Copilot instructions (concise)

This file gives targeted, repository-specific guidance so an AI coding agent can be productive immediately.

Editor quick notes: see `EDITOR_NOTES.md` at the repo root for a short, actionable checklist to follow when editing code.

1. Big picture
   - Multi-module Android POS app. Top-level modules: `app/`, `modules/feature-cart/`, `modules/feature-product/`, `modules/feature-reports/`, `shared-data/`.
   - App uses a local Room database (see `app/src/main/java/com/example/pos/data/local/entity/Entities.kt`) + remote API sync.
   - DI is used (Hilt) and features follow a Clean-ish separation: data (Room / Retrofit), domain (use-cases), ui (ViewModel + StateFlow).

2. Build / test / run (important commands)
   - Build debug APK: `./gradlew assembleDebug`
   - Run unit tests: `./gradlew test` (or `./gradlew :app:testDebugUnitTest` for module-scoped)
   - Run instrumentation tests (device/emulator): `./gradlew connectedDebugAndroidTest`
   - Clean & full build: `./gradlew clean build`

3. Project-specific conventions (do not invent alternatives)
   - Repository interfaces commonly prefixed with `I` (e.g. `IProductRepository`). Inject implementations via Hilt.
   - Use-cases are small classes named like `GetProductsUseCase` and injected into ViewModels.
   - ViewModels prefer Kotlin Coroutines + StateFlow (observe `modules/*/src` and `app/src` for examples).
   - Monetary values use integer cents (Long) — operate in cents to avoid FP errors; convert to display strings in UI.
   - Entities are located under `app/src/main/java/.../data/local/entity/` (see `Entities.kt` for canonical fields and naming).

4. Integration points & hardware
   - Hardware/vendor SDK wrappers are in a Java-compatible layer (look for `hardware/` or `app/src/.../hardware`). Keep JNI/Java wrappers isolated.
   - Sync flow: background `SyncManager` (or similarly named service) fetches remote data → maps to entities → upserts into Room.

5. Quick code examples (follow these shapes)
   - Repository interface: `interface IProductRepository { suspend fun getAll(): List<ProductEntity> }`
   - Use case: `class GetProductsUseCase @Inject constructor(private val repo: IProductRepository) { suspend operator fun invoke() = repo.getAll() }`
   - ViewModel: expose StateFlow, mutate with `viewModelScope.launch { _state.value = useCase() }`.

6. Where to look for answers in the tree
   - DI modules: search for `@Module` / `@InstallIn` in `app/src/main/java` and module `di` packages.
   - Database schema & migrations: `app/src/main/java/**/data/local/` and `app/build.gradle` Room deps.
   - Feature wiring: `modules/feature-*` folders for UI + ViewModel + use-case examples.

7. When you edit code
   - Keep public APIs stable: update repository interfaces and propagate to use-cases & ViewModels.
   - Add Room migrations if adding/removing columns (check existing `AppDatabase` or `Room.databaseBuilder` usage).
   - Unit tests: prefer `:module:testDebugUnitTest` for small changes before running full `./gradlew test`.

If any section is unclear or you want more examples (specific files, DI modules, or a canonical use-case), tell me which area and I will expand or sync the document.

---

## Archived / previous instructions (kept for reference)

```markdown
# Android POS App - Copilot Instructions

## Architecture Overview

This is a **Clean Architecture** Android POS (Point of Sale) application built with Kotlin/Java, following modern Android development patterns. The app supports retail/restaurant operations with features like product catalog, cart management, payments, inventory, and hardware integration.

### Key Architectural Patterns

-- **Clean Architecture**: Strict separation of concerns with `data`, `domain`, and `ui` layers
-- **Repository Pattern**: Abstract data access with `IProductRepository` interface
-- **MVVM**: ViewModels manage UI state with LiveData/Flow
-- **Dependency Injection**: Hilt for managing dependencies
-- **Offline-First**: Room database with sync capabilities via `SyncManager`

### Package Structure

```
com.example.pos/
├── di/                    # Hilt dependency injection modules
├── data/                  # Data layer
│   ├── local/            # Room database entities and DAOs
│   ├── remote/           # Retrofit API services
│   ├── repository/       # Repository implementations
│   └── SyncManager.kt    # Background sync coordination
├── domain/               # Business logic layer
│   ├── model/           # Domain models (CartItem)
│   └── usecase/         # Use cases (GetProductsUseCase)
├── ui/                   # Presentation layer
│   ├── login/           # Authentication screens
│   ├── product/         # Product catalog UI
│   ├── cart/            # Shopping cart management
│   ├── reports/         # Analytics and reporting
│   └── common/          # Shared UI components
├── util/                 # Utility classes
└── hardware/            # Hardware integration (Java compatibility layer)
```

## Development Workflow

### Building & Running

```bash
# Build the app
./gradlew assembleDebug

# Run unit tests
./gradlew testDebugUnitTest

# Run instrumentation tests
./gradlew connectedDebugAndroidTest

# Clean build
./gradlew clean build
```

### Key Development Commands

-- **Database Migration**: Use Room's `fallbackToDestructiveMigration()` for development
-- **Dependency Injection**: Always annotate injected classes with `@Inject` or `@HiltAndroidApp`
-- **Coroutines**: Use `viewModelScope.launch` for async operations in ViewModels

## Code Patterns & Conventions

### 1. Repository Pattern Implementation

```kotlin
// Interface in repository package
interface IProductRepository {
	suspend fun getAllProducts(): List<ProductEntity>
	suspend fun getProductById(id: String): ProductEntity?
	suspend fun upsert(product: ProductEntity)
}

// Implementation with @Singleton and @Inject
@Singleton
class ProductRepository @Inject constructor(
	private val dao: ProductDao
) : IProductRepository {
	override suspend fun getAllProducts(): List<ProductEntity> = dao.getAll()
}
```

### 2. Use Case Pattern

```kotlin
class GetProductsUseCase(private val repo: IProductRepository) {
	suspend operator fun invoke(): List<ProductEntity> = repo.getAllProducts()
}
```

### 3. ViewModel with StateFlow

```kotlin
class ProductViewModel(private val getProducts: GetProductsUseCase) : ViewModel() {
	private val _state = MutableStateFlow<List<ProductEntity>>(emptyList())
	val state: StateFlow<List<ProductEntity>> = _state

	fun load() {
		viewModelScope.launch {
			_state.value = getProducts()
		}
	}
}
```

### 4. Cart Management Pattern

```kotlin
class CartViewModel : ViewModel() {
	private val _items = MutableStateFlow<List<CartItem>>(emptyList())
	val items: StateFlow<List<CartItem>> = _items

	fun addItem(item: CartItem) {
		val current = _items.value.toMutableList()
		val existing = current.indexOfFirst { it.productId == item.productId }
		if (existing >= 0) {
			current[existing].qty += item.qty  // Update quantity
		} else {
			current.add(item)  // Add new item
		}
		_items.value = current
	}
}
```

### 5. Hardware Integration (Java Compatibility)

```java
public class PrinterService {
	// Java-based compatibility layer for older libraries / SDKs
	public boolean print(String text) {
		// TODO: Wrap vendor SDK
		System.out.println("PRINT: " + text);
		return true;
	}
}
```

## Data Flow Patterns

### Product Loading Flow
1. `ProductViewModel.load()` → `GetProductsUseCase()` → `ProductRepository.getAllProducts()` → `ProductDao.getAll()`
2. Results flow back through StateFlow to UI

### Cart Operations Flow
1. UI calls `CartViewModel.addItem()` → Updates internal StateFlow
2. UI observes `CartViewModel.items` StateFlow for real-time updates
3. `CartViewModel.totalAmountCents()` calculates derived state

### Sync Flow
1. `SyncManager.syncOnce()` → `ApiService.fetchProducts()` → Map to entities → `ProductDao.upsert()`
2. Error handling with offline fallback

## Key Files to Reference

-- **`App.kt`**: Hilt application setup
-- **`AppModule.kt`**: DI configuration for database and repositories
-- **`AppDatabase.kt`**: Room database configuration
-- **`SyncManager.kt`**: Background sync coordination
-- **`CartViewModel.kt`**: Cart state management example

## Hardware Integration Strategy

-- Use Java classes in `hardware/` package for vendor SDK compatibility
-- Camera scanning via Android's CameraX or ML Kit
-- Receipt printing via vendor-specific SDKs wrapped in Java services
-- Cash drawer control through serial/USB connections

## Testing Strategy

-- **Unit Tests**: Use cases, repositories, ViewModels with MockK
-- **Integration Tests**: Database operations, API calls
-- **UI Tests**: Key user flows (login → product selection → cart → payment)

## Backend Integration

-- **REST API**: Retrofit with Moshi for JSON serialization
-- **Offline Mode**: Room database as single source of truth
-- **Sync Strategy**: Periodic background sync with conflict resolution
-- **Authentication**: JWT tokens stored in secure preferences

## Common Patterns to Follow

1. **Price Handling**: Always use cents (Long) to avoid floating-point precision issues
2. **Error Handling**: Use try-catch in suspend functions with fallback strategies
3. **State Management**: Prefer StateFlow over LiveData for reactive UI updates
4. **Dependency Injection**: Inject repositories into use cases, use cases into ViewModels
5. **Currency**: All transactions are in Malaysian Ringgit (RM/MYR). Never use dollar signs ($) - always use RM or MYR
6. **Naming**: Use descriptive names like `IProductRepository`, `GetProductsUseCase`

## Module Organization

-- **Feature Modules**: Separate modules for `feature-cart`, `feature-product`, `feature-reports`
-- **Shared Data**: Common data models and utilities in `shared-data` module
-- **Gradle Configuration**: Separate build files for each module with specific dependencies

``` 
