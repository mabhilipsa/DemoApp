plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.nisum.demo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nisum.demo"
        minSdk = 24
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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.adapters)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.coroutines.android)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    //Hilt Navigation Compose
    implementation(libs.hilt.navigation.compose)
    implementation(libs.gson)
    // Retrofit
    api(libs.retrofit)
    implementation(libs.retrofit) // Retrofit
    implementation(libs.converterGson) // Gson Converter
    implementation(libs.bundles.coil)
    implementation(libs.bundles.paging)

    testImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    testImplementation("androidx.paging:paging-common-ktx:3.2.1")
    testImplementation("com.google.truth:truth:1.1.3")
    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    // Room
    implementation("androidx.room:room-runtime:2.6.1") // Or latest
    kapt("androidx.room:room-compiler:2.6.1") // Or latest
    implementation("androidx.room:room-ktx:2.6.1") // Or latest (Optional, for Kotlin coroutines support)

    // Room Paging Integration
    implementation("androidx.room:room-paging:2.6.1") // Or latest - THIS IS THE KEY ADDITION

    // Paging 3
    implementation("androidx.paging:paging-runtime-ktx:3.2.1") // Or latest
    implementation("androidx.paging:paging-compose:3.2.1") // Or latest
}


