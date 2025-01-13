plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.google.firebase.crashlytics)
    alias(libs.plugins.google.firebase.firebase.perf)
}

android {
    namespace = "com.example.cook_ford"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.cook_ford"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.testing)
    implementation(libs.androidx.material.iconsext)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.ui.util)
    implementation(libs.androidx.coroutines.android)
    implementation(libs.androidx.coroutines.test)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.retrofit2.retrofit)
    implementation(libs.androidx.retrofit2.converter.gson)
    implementation(libs.androidx.converter.moshi)
    implementation(libs.androidx.okhttp.okhttp)
    implementation(libs.androidx.okhttp.mockWebServer)
    implementation(libs.volley)
    implementation(libs.androidx.datastore.core.android)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.bundles.firebase)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.firebase.database.ktx)
    implementation(libs.androidx.storage)
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.androidx.databinding.runtime)
    implementation(libs.androidx.accompanist.pager)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.material.android)
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.coil.compose)
    implementation(libs.androidx.coil.gif)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.glide)
    implementation(libs.google.play.services.location )
    implementation(libs.accompanist.permissions)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
