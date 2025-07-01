plugins {
    alias(libs.plugins.myScaffold.android.library)
}

android {
    namespace = "com.sahsenvar.database"
}

dependencies {
    api(projects.core.model)

}