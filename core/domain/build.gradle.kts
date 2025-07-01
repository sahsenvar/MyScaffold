plugins {
    alias(libs.plugins.myScaffold.android.library)
}

android {
    namespace = "com.sahsenvar.domain"

}

dependencies {
    api(projects.core.data)
    api(projects.core.model)
}