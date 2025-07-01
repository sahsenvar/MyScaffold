plugins {
    alias(libs.plugins.myScaffold.android.library)
}

android {
    namespace = "com.sahsenvar.ui"
}

dependencies {
    api(projects.core.analytics)
    api(projects.core.designsystem)
    api(projects.core.model)

}