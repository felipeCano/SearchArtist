plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.search.artist"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.search.artist"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}
detekt {
    toolVersion = libs.versions.detekt.get()
    autoCorrect = true
    ignoreFailures = false
    buildUponDefaultConfig = true
    config.setFrom(files("$projectDir/config/detekt/detekt.yml"))
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    //Gson
    implementation (libs.gson)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.retrofit2.converter.gson)
    //Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    //LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // Annotation processor
    kapt(libs.androidx.lifecycle.compiler)
    //hilt
    implementation(libs.google.dagger.hilt)
    kapt(libs.google.dagger.hilt.compiler)
    //Compose + Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    //Navigation Compose
    implementation(libs.androidx.navigation.compose)
    //Coil
    implementation(libs.coil.compose)
}

kapt {
    correctErrorTypes = true
}