plugins {
    alias(libs.plugins.myScaffold.android.library)
}

android {
    namespace = "com.sahsenvar.network"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.core.model)
}