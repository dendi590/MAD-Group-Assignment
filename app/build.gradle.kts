plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "my.edu.utem"
    compileSdk = 34

    defaultConfig {
        applicationId = "my.edu.utem"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // CHANGED: Replaced 'libs.androidx.activity' with a specific version compatible with SDK 34
    // and combined it with the activity-ktx dependency to avoid conflicts.
    implementation("androidx.activity:activity-ktx:1.9.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.1.0")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // REMOVED: Duplicate entry "androidx.activity:activity-ktx:1.8.2"
    // (This is now covered by the 1.9.0 version added above)
}
