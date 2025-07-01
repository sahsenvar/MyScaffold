plugins {
    alias(libs.plugins.myScaffold.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sahsenvar.persistence"
}

dependencies {
    api(projects.core.model)
}