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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.7.0")
    implementation(libs.coroutines.android)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
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
    jvmToolchain(17)
}

// Ensure Kotlin compilation targets JVM 17 using the compilerOptions DSL
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

// KAPT configuration
kapt {
    correctErrorTypes = true
}
