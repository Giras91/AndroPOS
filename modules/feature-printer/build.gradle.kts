plugins {
    id("com.android.library")
    kotlin("android")
    id("org.jetbrains.kotlin.kapt")
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
}

dependencies {
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.1.0")
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-compiler:2.52")
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
