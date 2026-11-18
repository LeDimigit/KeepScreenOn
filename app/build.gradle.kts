plugins {
    id("com.android.application")
}

android {
    namespace = "org.libre.keepscreenon"
    compileSdk = 36
    defaultConfig {
        applicationId = "org.libre.keepscreenon"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"
    }
    buildTypes { release { isMinifyEnabled = true } }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.16.0") // Apache 2.0
}
