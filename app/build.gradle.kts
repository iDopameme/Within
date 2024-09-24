import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.google.protobuf)
    alias(libs.plugins.kotlin.parcelize)
}

androidComponents {
    onVariants(selector().all()) { variant ->
        afterEvaluate {
            val capName = variant.name.capitalized()
            tasks.getByName<KotlinCompile>("ksp${capName}Kotlin") {
                setSource(tasks.getByName("generate${capName}Proto").outputs)
            }
        }
    }
}

android {
    namespace = "com.rjwalker.within"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rjwalker.within"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isDebuggable = true
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {

    // Android Test Dependencies
    androidTestImplementation(libs.testing)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // Activity Compose
    implementation(libs.androidx.activity.compose)

    // AndroidX Core KTX
    implementation(libs.androidx.core.ktx)

    // Compose Runtime
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.tracing.ktx)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.runtimeTesting)

    // Material 3
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.adaptive.layout)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.material3.adaptive.navigation.suite.android)

    // Datastore
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Compose UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.androidx.ui.tooling)

    // ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.serialization.kotlinx.protobuf)
    implementation(libs.ktor.client.content.negotiation)

    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.dagger.hilt.testing)

    // Room Database
    implementation(libs.room.ktx)
    implementation(libs.room.paging)

    // Firebase
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)

    // ProtoBuf
    implementation(libs.protobuf.kotlin.lite)

    // Hilt Navigation
    implementation(libs.hilt.navigation.compose)

    // Datetime
    implementation(libs.kotlinx.datetime)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Kotlin
    implementation(libs.kotlin.stdlib)

    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))

    // Firebase BOM
    implementation(platform(libs.firebase.bom))

    // KSP
    ksp(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
    ksp(libs.room.compiler)

    testImplementation(libs.room.testing)
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.junit)
    testImplementation(libs.app.cash.turbine)
}