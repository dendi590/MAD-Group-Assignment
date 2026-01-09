plugins {
<<<<<<< HEAD
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "my.student.edu.utem.taskmasterpro"
    compileSdk = 36

    defaultConfig {
        applicationId = "my.student.edu.utem.taskmasterpro"
        minSdk = 28
        targetSdk = 36
=======
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
>>>>>>> nabila_branch
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
<<<<<<< HEAD

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
=======
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
>>>>>>> nabila_branch
    }
}

dependencies {
<<<<<<< HEAD
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.google.code.gson:gson:2.10.1")

=======
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
>>>>>>> nabila_branch
}
