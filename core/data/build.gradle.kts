plugins {
    alias(libs.plugins.myScaffold.android.library)
}

android {
    namespace = "com.sahsenvar.data"
    testOptions{
        unitTests{
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.core.database)
    api(projects.core.persistence)
    api(projects.core.network)

    implementation(projects.core.analytics)
    //todo: implementation(projects.core.notifications)
}