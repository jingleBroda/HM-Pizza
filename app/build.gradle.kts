plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.hm_pizza"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.hm_pizza"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        android.buildFeatures.buildConfig = true
        buildConfigField("String", "APPLICATION_ID", "\"${property("API_APPLICATION_ID")}\"")
        buildConfigField("String", "APPLICATION_KEY", "\"${property("API_APPLICATION_KEY")}\"")

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
    val dagger2_version = "2.48"
    val coroutin_version = "1.6.1"
    val room_version = "2.4.3"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //dagger2
    implementation ("com.google.dagger:dagger:$dagger2_version")
    kapt ("com.google.dagger:dagger-compiler:$dagger2_version")
    //dagger2 androidInject
    implementation ("com.google.dagger:dagger-android:$dagger2_version")
    implementation ("com.google.dagger:dagger-android-support:$dagger2_version")
    kapt ("com.google.dagger:dagger-android-processor:$dagger2_version")

    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutin_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutin_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.2")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    //Room
    implementation ("androidx.room:room-ktx:$room_version")
    implementation ("androidx.room:room-runtime:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")
    kapt ("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")

    implementation(project(":presentation"))
    implementation(project(":data"))
    implementation(project(":domain"))
}