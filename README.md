<<<<<<< HEAD
# AndroPOS - Android Point of Sale

A modern Android POS (Point of Sale) application built with Kotlin, following Clean Architecture principles.

<!-- CI status badges -->
[![PR - Quick checks](https://github.com/Giras91/AndroPOS/actions/workflows/pr-check.yml/badge.svg?branch=main)](https://github.com/Giras91/AndroPOS/actions/workflows/pr-check.yml)
[![Nightly integration](https://github.com/Giras91/AndroPOS/actions/workflows/nightly-integration.yml/badge.svg?branch=main)](https://github.com/Giras91/AndroPOS/actions/workflows/nightly-integration.yml)

## Features

- **User Authentication**: Login screen with secure authentication
- **Product Catalog**: Browse and search products by department
- **Cart Management**: Add/remove items, apply discounts, manage quantities
- **Payment Processing**: Support for cash, card, and voucher payments
- **Ticket Management**: Create, suspend, recall, and complete transactions
- **Inventory Management**: Track stock levels and adjust inventory
- **Reports**: Daily/hourly sales reports and transaction history
- **Hardware Integration**: Barcode scanning, receipt printing, cash drawer control
- **Multi-store Support**: Handle multiple store locations
- **Offline Mode**: Continue operations when network is unavailable

## Architecture

This app follows **Clean Architecture** with strict separation of concerns:

```
    com.extrotarget.extropos/
├── di/                    # Hilt dependency injection modules
├── data/                  # Data layer
│   ├── local/            # Room database entities and DAOs
│   ├── remote/           # Retrofit API services
│   └── repository/       # Repository implementations
├── domain/               # Business logic layer
│   ├── model/           # Domain models (Ticket, CartItem, etc.)
│   └── usecase/         # Use cases for business operations
├── ui/                   # Presentation layer
│   ├── login/           # Authentication screens
│   ├── product/         # Product catalog UI
│   ├── cart/            # Shopping cart management
│   ├── reports/         # Analytics and reporting
│   └── common/          # Shared UI components
├── util/                 # Utility classes
└── hardware/            # Hardware integration (Java compatibility layer)
```

## Technology Stack

- **Language**: Kotlin (primary), Java (for hardware compatibility)
- **Architecture**: Clean Architecture + MVVM
- **Dependency Injection**: Hilt
- **Database**: Room
- **Networking**: Retrofit + Moshi
- **Async**: Coroutines + Flow
- **UI**: ViewModel + LiveData/StateFlow

<!-- CI status badge -->
[![PR - Quick checks](https://github.com/ExtroTargetSdnBhd/AndroPOS/actions/workflows/pr-check.yml/badge.svg?branch=main)](https://github.com/ExtroTargetSdnBhd/AndroPOS/actions/workflows/pr-check.yml)

[![Nightly integration](https://github.com/ExtroTargetSdnBhd/AndroPOS/actions/workflows/nightly-integration.yml/badge.svg?branch=main)](https://github.com/ExtroTargetSdnBhd/AndroPOS/actions/workflows/nightly-integration.yml)

## Key Patterns

### Price Handling
All monetary values are stored in **cents** (Long) to avoid floating-point precision issues.

### Currency
All transactions use **Malaysian Ringgit (RM/MYR)**. Never use dollar signs ($).

### Repository Pattern
```kotlin
interface IProductRepository {
    suspend fun getAllProducts(): List<ProductEntity>
}

@Singleton
class ProductRepository @Inject constructor(
    private val dao: ProductDao
) : IProductRepository
```

### Use Case Pattern
```kotlin
class GetProductsUseCase(private val repo: IProductRepository) {
    suspend operator fun invoke(): List<ProductEntity> = repo.getAllProducts()
}
```

### ViewModel with StateFlow
```kotlin
class ProductViewModel(private val getProducts: GetProductsUseCase) : ViewModel() {
    private val _state = MutableStateFlow<List<ProductEntity>>(emptyList())
    val state: StateFlow<List<ProductEntity>> = _state
}
```

## Building & Running

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

## Java 21 & Kotlin Version Alignment (Build Notes)

The project has been upgraded to run on **Java 21** (Gradle toolchain + Android compileOptions + Kotlin jvmTarget). During this upgrade we observed a Kotlin metadata version mismatch in annotation processing (Hilt/Room) when transitive dependencies brought in Kotlin 2.2.x metadata while processors only supported up to 2.1.x.

To provide a stable, passing build quickly, we currently **force Kotlin artifacts to 2.1.0** and `kotlinx-metadata-jvm` to `0.7.0` via a Gradle `resolutionStrategy` in the root `build.gradle`. This keeps all generated Kotlin metadata at a version the bundled/shaded processors can read.

Summary of current alignment:
- Java toolchain / source & target: 21
- Kotlin plugin & stdlib: 2.1.0 (forced)
- Hilt: 2.52
- Room: 2.7.0
- kotlinx-metadata-jvm (explicit for kapt): 0.7.0

Why not Kotlin 2.2.x yet?
- Hilt / Room processors (shaded XProcessing + metadata reader) threw: `Provided Metadata instance has version 2.2.0, while maximum supported version is 2.1.0.` Upgrading Kotlin alone did not resolve this because the processors’ shaded metadata reader still rejected 2.2.x.

Upgrade path (future):
1. Monitor releases of Hilt and Room for support of Kotlin 2.2.x metadata (or newer kotlinx-metadata-jvm internally).
2. Remove / adjust the forced versions in `build.gradle` once processors accept newer metadata.
3. Bump Kotlin plugin & stdlib, re-run `assembleDebug`, then full tests.
4. Remove deprecated Groovy DSL warnings entirely (remaining minor cleanup) and consider migrating Groovy build files to Kotlin DSL for consistency.

If you start a new feature module and need Kotlin 2.2.x features, first update processors as above; mixing metadata versions will reintroduce kapt failures.

This section should be removed once the project is fully on a newer Kotlin version without forcing.

## Development Setup

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run on device/emulator

## Database Schema

The app uses Room database with the following main entities:
- `products`: Product catalog
- `departments`: Product categories
- `tickets`: Transaction records
- `ticket_items`: Items in each transaction
- `ticket_tenders`: Payment records
- `tenders`: Payment methods
- `tax_groups`: Tax configurations

## API Integration

The app supports REST API integration for:
- Product synchronization
- Transaction upload
- Inventory updates
- User authentication

## Hardware Integration

Java-based compatibility layer for hardware peripherals:
- Barcode scanners (camera-based)
- Receipt printers
- Cash drawers
- Payment terminals

## Contributing

1. Follow the established architecture patterns
2. Write unit tests for new features
3. Use meaningful commit messages
4. Update documentation as needed

## License

This project is licensed under the Apache License 2.0.
