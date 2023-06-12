import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
}

val properties = Properties()
val messagesProperties = Properties()

android {
    namespace = "dev.brunofelix.marvelapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "dev.brunofelix.marvelapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            // Marvel API
            properties.load(FileInputStream(project.rootProject.file("api.properties")))
            messagesProperties.load(FileInputStream(project.rootProject.file("messages.properties")))

            buildConfigField("String", "BASE_URL", "\"${properties.getProperty("base_url")}\"")
            buildConfigField("String", "PUBLIC_KEY", "\"${properties.getProperty("public_key")}\"")
            buildConfigField("String", "PRIVATE_KEY", "\"${properties.getProperty("private_key")}\"")

            buildConfigField("String", "UNKNOWN_ERROR", "\"${messagesProperties.getProperty("msg_unknown_error")}\"")
            buildConfigField("String", "HOST_ERROR", "\"${messagesProperties.getProperty("msg_unknown_host")}\"")
            buildConfigField("String", "SERVER_ERROR", "\"${messagesProperties.getProperty("msg_connection_error")}\"")

            buildConfigField("String", "EMPTY_LIST", "\"${messagesProperties.getProperty("msg_empty")}\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    // Kotlin(Core
    implementation("androidx.core:core-ktx:1.10.1")

    // Activity / Fragment KTX for viewModels()
    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("androidx.fragment:fragment-ktx:1.5.7")

    // AppCompat
    implementation("androidx.appcompat:appcompat:1.6.1")

    // Material Design
    implementation("com.google.android.material:material:1.9.0")

    // ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Timber
    implementation("com.jakewharton.timber:timber:4.7.1")

    // Gson
    implementation("com.google.code.gson:gson:2.9.0")

    // Preferences
    implementation("androidx.preference:preference-ktx:1.2.0")

    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Architecture Component
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")

    // Navigation(Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.46.1")
    kapt("com.google.dagger:hilt-compiler:2.46.1")

    //  Room
    implementation("androidx.room:room-runtime:2.5.1")
    implementation("androidx.room:room-ktx:2.5.1")
    implementation("androidx.room:room-paging:2.5.1")
    ksp("androidx.room:room-compiler:2.5.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")

    // Paging
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")

    // Unit Tests
    testImplementation("junit:junit:4.13.2")

    // Instrumented / UI Tests
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}