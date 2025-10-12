plugins {
    id("com.android.library")
    kotlin("android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.extrotarget.extropos.printer"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    lint {
        abortOnError = false
    }
}

dependencies {
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.7.0")
    implementation(libs.coroutines.android)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    
    // Android UI Components
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.material)
    
    // DataStore and Serialization
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    
    // DantSu ESC/POS Printer SDK
    implementation("com.github.DantSu:ESCPOS-ThermalPrinter-Android:3.3.0")
    
    testImplementation("junit:junit:4.13.2")
}

// If you add vendor SDK AARs into modules/feature-printer/libs/, include them automatically
val aarFiles = file("libs").listFiles()?.filter { it.extension == "aar" } ?: emptyList()
if (aarFiles.isNotEmpty()) {
    dependencies {
        aarFiles.forEach { f ->
            implementation(files("libs/${f.name}"))
        }
    }
}

// Use Kotlin JVM toolchain to ensure kapt and javac target the same Java version
kotlin {
    jvmToolchain(21)
}

// Ensure Kotlin compilation targets JVM 21 using the compilerOptions DSL
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}

// KAPT configuration
kapt {
    correctErrorTypes = true
}
