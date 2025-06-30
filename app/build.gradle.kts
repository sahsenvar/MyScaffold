import com.sahsenvar.genlib.genlibs

plugins {
    alias(libs.plugins.myScaffold.android.application)
    alias(libs.plugins.myScaffold.android.application.compose)
}

android {
    namespace = "com.sahsenvar.myscaffold"
    defaultConfig{
        applicationId = "com.sahsenvar.myscaffold"
        versionCode = genlibs.Versions.versionCode.toInt()
        versionName = genlibs.Versions.versionName
    }
}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}