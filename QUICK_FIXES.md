# Quick Fixes for Critical Issues

This document provides immediate fixes for the most critical issues blocking production deployment.

## 1. Fix Gradle Wrapper (P0 - Blocker)

The current `gradlew` script has syntax errors. Replace it with a working version:

```bash
# Download fresh Gradle wrapper
gradle wrapper --gradle-version 8.1.4

# Or use system Gradle for now
alias gradlew='gradle'
```

## 2. Fix Package Structure (P0 - Blocker)

Choose one package structure and consolidate:

**Option A: Use `com.extrotarget.extropos`** (Recommended)
- Move all files from `com.example.pos` to `com.extrotarget.extropos`
- Update all imports and references

**Option B: Use `com.example.pos`**
- Move all files from `com.extrotarget.extropos` to `com.example.pos`
- Update build.gradle applicationId

## 3. Create Missing ProductDao and ProductEntity (P0 - Blocker)

Create in `com.example.pos.data.local.dao.ProductDao.kt`:
```kotlin
@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    suspend fun getAll(): List<ProductEntity>
    
    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getById(id: String): ProductEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg products: ProductEntity)
}
```

Create in `com.example.pos.data.local.entity.ProductEntity.kt`:
```kotlin
@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val priceCents: Long,
    val sku: String?,
    val stockQuantity: Int
)
```

## 4. Fix Duplicate AppModule (P0 - Blocker)

Remove the duplicate `AppModule` definition (lines 75-118) in `AppModule.kt`.

## 5. Remove Security Vulnerabilities (P0 - Blocker)

### Move API keys to BuildConfig:

In `app/build.gradle`:
```gradle
android {
    buildTypes {
        debug {
            buildConfigField "String", "APPWRITE_API_KEY", "\"$APPWRITE_API_KEY\""
        }
        release {
            buildConfigField "String", "APPWRITE_API_KEY", "\"$APPWRITE_API_KEY\""
        }
    }
}
```

### Update AppwriteConfig.kt:
```kotlin
object AppwriteConfig {
    const val APPWRITE_PROJECT_ID = "68da3df3003cad66fe0f"
    const val APPWRITE_PROJECT_NAME = "ExtroTarget"
    const val APPWRITE_PUBLIC_ENDPOINT = "https://syd.cloud.appwrite.io/v1"
    
    // Get from BuildConfig instead of hardcoding
    val APPWRITE_API_KEY: String get() = BuildConfig.APPWRITE_API_KEY
}
```

### Remove development flags from production:

In `App.kt`:
```kotlin
override fun onCreate() {
    super.onCreate()
    
    appwriteClient = Client(this)
        .setEndpoint(AppwriteConfig.APPWRITE_PUBLIC_ENDPOINT)
        .setProject(AppwriteConfig.APPWRITE_PROJECT_ID)
        // Remove .setSelfSigned(true) for production
}
```

In `AndroidManifest.xml`:
```xml
<application
    android:name=".App"
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:theme="@style/AppTheme">
    <!-- Remove android:usesCleartextTraffic="true" for production -->
```

## 6. Set Up Room Database (P0 - Blocker)

Create `AppDatabase.kt`:
```kotlin
@Database(
    entities = [ProductEntity::class, TicketEntity::class, /* other entities */],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun ticketDao(): TicketDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pos_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
```

Update `AppModule.kt`:
```kotlin
@Provides
@Singleton
fun provideDatabase(context: Context): AppDatabase {
    return AppDatabase.getDatabase(context)
}

@Provides
fun provideProductDao(database: AppDatabase): ProductDao {
    return database.productDao()
}
```

## 7. Add Basic Test Structure (P0 - Blocker)

Create test directories:
```bash
mkdir -p app/src/test/java/com/extrotarget/extropos
mkdir -p app/src/androidTest/java/com/extrotarget/extropos
```

Add basic test in `app/src/test/java/.../ProductRepositoryTest.kt`:
```kotlin
@RunWith(MockitoJUnitRunner::class)
class ProductRepositoryTest {
    
    @Mock
    private lateinit var productDao: ProductDao
    
    private lateinit var repository: ProductRepository
    
    @Before
    fun setup() {
        repository = ProductRepository(productDao)
    }
    
    @Test
    fun `getAllProducts returns products from dao`() = runTest {
        // Given
        val entities = listOf(
            ProductEntity("1", "Product 1", 1000, "SKU1", 10)
        )
        whenever(productDao.getAll()).thenReturn(entities)
        
        // When
        val result = repository.getAllProducts()
        
        // Then
        assertEquals(1, result.size)
        assertEquals("Product 1", result[0].name)
    }
}
```

## 8. Fix Build Dependencies

Add test dependencies in `app/build.gradle`:
```gradle
dependencies {
    // ... existing dependencies ...
    
    // Testing
    testImplementation 'junit:junit:4.13.2'
    testImplementation 'org.mockito:mockito-core:5.1.1'
    testImplementation 'org.mockito.kotlin:mockito-kotlin:4.1.0'
    testImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3'
    testImplementation 'androidx.arch.core:core-testing:2.2.0'
    
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'  
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
}
```

## Verification Steps

After applying these fixes:

1. **Build Test**:
   ```bash
   ./gradlew assembleDebug
   ```

2. **Run Tests**:
   ```bash
   ./gradlew test
   ```

3. **Check Code**:
   ```bash
   # If ktlint is available
   ./gradlew ktlintCheck
   ```

4. **Security Check**:
   - Verify no hardcoded secrets remain
   - Check that development flags are removed

## Next Steps

After these critical fixes:
1. Implement comprehensive test coverage
2. Add proper error handling and logging
3. Set up continuous integration
4. Conduct security audit
5. Performance testing with real data

These fixes address the most critical blocking issues and should allow the project to build and run safely.