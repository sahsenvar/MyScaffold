import com.sahsenvar.myscaffold.AppBuildType

plugins {
    alias(libs.plugins.myScaffold.android.application)
    alias(libs.plugins.myScaffold.android.application.compose)
}

android {
    namespace = "com.sahsenvar.app_catalog"
    defaultConfig {
        applicationId = "com.sahsenvar.app_catalog"
        versionCode = 1
        versionName = "0.0.1"
    }

    buildTypes {
        all{
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles.clear()
            applicationIdSuffix = null
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
}