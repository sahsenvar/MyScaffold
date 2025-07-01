@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import tasks.GenerateGenLibTask

plugins {
    `kotlin-dsl`
}

group = "com.sahsenvar.myscaffold.buildlogic.convention"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.buildLogic.android.tools.common)
    compileOnly(libs.buildLogic.android.tools.gradlePlugin)
    compileOnly(libs.buildLogic.kotlin.gradlePlugin)
    compileOnly(libs.buildLogic.kotlin.compose.gradlePlugin)
}

tasks.register("generateGenLib", GenerateGenLibTask::class.java)
tasks {
    named("checkKotlinGradlePluginConfigurationErrors") {
        dependsOn("generateGenLib")
    }

    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}


gradlePlugin {
    plugins {
        register("AndroidApplicationConventionPlugin") {
            id = libs.plugins.myScaffold.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("AndroidApplicationComposeConventionPlugin") {
            id = libs.plugins.myScaffold.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("AndroidLibraryConventionPlugin") {
            id = libs.plugins.myScaffold.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }

    }
}

kotlin.sourceSets["main"].kotlin.srcDir("../build/generated")

