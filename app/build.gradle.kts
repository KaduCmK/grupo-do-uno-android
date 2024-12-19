plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.gms)
    alias(libs.plugins.kotlin.serialization)
}

android {
    signingConfigs {
        create("io.github.kaducmk.grupodouno.debug") {
            storeFile = file("C:\\Users\\carlos.alves.SECONCI\\pica.jks")
            storePassword = "Kadu2014"
            keyAlias = "io.github.kaducmk.grupodouno.debug"
            keyPassword = "@Kadu2014"
        }
        create("io.github.kaducmk.grupodouno.release") {
            storeFile = file("C:\\Users\\carlos.alves.SECONCI\\pica.jks")
            storePassword = "Kadu2014"
            keyAlias = "io.github.kaducmk.grupodouno.release"
            keyPassword = "@Kadu2014"
        }
    }
    namespace = "io.github.kaducmk.grupodouno"
    compileSdk = 35

    defaultConfig {
        applicationId = "io.github.kaducmk.grupodouno"
        minSdk = 28
        targetSdk = 35
        versionCode = 2
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfig = signingConfigs.getByName("io.github.kaducmk.grupodouno.debug")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("io.github.kaducmk.grupodouno.release")
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("io.github.kaducmk.grupodouno.debug")
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
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.room.runtime)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.coil.compose)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.googleid)
    ksp(libs.androidx.room.compiler)
    ksp(libs.hilt.android.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}