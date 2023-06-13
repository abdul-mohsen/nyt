import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.serialization)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

val APIKey: String = gradleLocalProperties(rootDir).getProperty("APIKey")

android {
    namespace = "com.example.nyt"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.nyt"
        minSdk = 24
        targetSdk = 33
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
            buildConfigField("String", "APIKey", APIKey)
            buildConfigField("String", "BaseUrl", "\"https://api.nytimes.com\"")
            buildConfigField("String", "MostPopularApi", "\"/svc/mostpopular/v2/viewed/{type}.json\"")

        }
        debug {

            buildConfigField("String", "APIKey", APIKey)
            buildConfigField("String", "BaseUrl", "\"https://api.nytimes.com\"")
            buildConfigField("String", "MostPopularApi", "\"/svc/mostpopular/v2/viewed/{type}.json\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        // disable warring for experimental api
        freeCompilerArgs += listOf(
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
        )
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

hilt {
    enableAggregatingTask = true
}

kapt {
    useBuildCache = false
}

dependencies {
//    kapt(libs.artifactId)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.hilt)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.okhttp)
    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.androidx.material)
    kapt(libs.hiltk)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}