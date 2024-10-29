plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            // Ktor Client Dependencies
            implementation(libs.ktor.client.core)

            // Coroutines to perform network requests without block the main thread
            implementation(libs.kotlinx.coroutines.core)
        }
        androidMain.dependencies {
            // Ktor Client Dependencies - Engine for Android
            // A specific platform may require a specific engine that processes network requests.
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            // Ktor Client Dependencies - Engine for iOS
            // A specific platform may require a specific engine that processes network requests.
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.codeplace.ktorclientkmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 25
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
