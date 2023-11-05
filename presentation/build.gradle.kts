plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.presentation"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    viewBinding{
        enable = true
    }
}

dependencies {
    val dagger2_version = "2.48"
    val coroutin_version = "1.6.1"
    val navigate_version = "2.5.2"
    val glide_version = "4.14.2"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Fragment+Lifecycle
    implementation ("androidx.fragment:fragment-ktx:1.6.1")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutin_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutin_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.2")

    // Jetpack Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:$navigate_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$navigate_version")

    //Dagger2
    implementation ("com.google.dagger:dagger:$dagger2_version")
    kapt ("com.google.dagger:dagger-compiler:$dagger2_version")
    //Dagger2 Android Support
    implementation ("com.google.dagger:dagger-android:$dagger2_version")
    implementation ("com.google.dagger:dagger-android-support:$dagger2_version")
    annotationProcessor ("com.google.dagger:dagger-android-processor:$dagger2_version")

    //glide
    implementation ("com.github.bumptech.glide:glide:$glide_version")
    kapt ("com.github.bumptech.glide:compiler:$glide_version")

    implementation(project(":domain"))
}