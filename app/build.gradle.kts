plugins {
    alias(libs.plugins.androidApplication)
// Add the Google services Gradle plugin
    id("com.google.gms.google-services")

}

android {
    namespace = "com.bhojan"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bhojan"
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
}

dependencies {
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")
    implementation ("com.google.android.material:material:1.4.0")
    implementation ("com.google.firebase:firebase-firestore:23.0.1")


    implementation ("com.google.android.gms:play-services-auth:19.2.0")
    implementation ("com.facebook.android:facebook-login:8.2.0")
    implementation ("com.google.firebase:firebase-auth:19.3.1")
    implementation ("com.firebaseui:firebase-ui-database:8.0.2")
    implementation ("com.firebaseui:firebase-ui-firestore:8.0.2")
    implementation ("com.google.firebase:firebase-firestore:24.1.0")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("com.google.firebase:firebase-storage:20.0.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.3.1")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.cardview)
    implementation(libs.navigation.fragment)
    implementation(libs.firebase.storage)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}