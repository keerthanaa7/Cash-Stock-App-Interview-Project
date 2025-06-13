plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")

}

android {
    namespace = "com.example.cashappstocks"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.cashappstocks"
        minSdk = 24
        targetSdk = 35
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
    }
}

dependencies {
    val arch_version = "2.2.0"
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
  //  testImplementation(libs.junit)
  //  androidTestImplementation(libs.androidx.junit)

  //  androidTestImplementation(platform(libs.androidx.compose.bom))
  //  androidTestImplementation(libs.androidx.ui.test.junit4)
  //  debugImplementation(libs.androidx.ui.tooling)
  //  debugImplementation(libs.androidx.ui.test.manifest)

    // gradle dependency for adding retrofit library
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.9.1")


    // kotlin coroutine dependency
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")

    kapt("androidx.lifecycle:lifecycle-compiler:2.7.0")

    // optional - Test helpers for LiveData


    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")


    // OKhttp dependency
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    testImplementation("androidx.arch.core:core-testing:$arch_version")
    testImplementation("androidx.lifecycle:lifecycle-runtime-testing:2.9.1")
    testImplementation("com.google.truth:truth:1.4.4")
    testImplementation("com.google.truth.extensions:truth-java8-extension:1.1.3")
    testImplementation("org.mockito:mockito-core:5.0.0")
    // build.gradle.kts or build.gradle
    testImplementation("io.mockk:mockk:1.13.7") // or latest
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3") // or latest


}