plugins {
    alias(libs.plugins.myScaffold.android.application)
    alias(libs.plugins.myScaffold.android.application.compose)
}

android {
    namespace = "com.sahsenvar.app_catalog"
    defaultConfig {
        applicationId = "com.sahsenvar.app_catalog"
        versionCode = 1
        versionName = "1.0.0"
    }

    buildFeatures.compose = true

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //androidTestImplementation(platform(libs.androidx.compose.bom))
    //androidTestImplementation(libs.androidx.ui.test.junit4)
    //debugImplementation(libs.androidx.ui.tooling)
    //debugImplementation(libs.androidx.ui.test.manifest)
}