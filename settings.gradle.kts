@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyScaffold"
// composite build
includeBuild("build-logic")

// Apps
include(":app")
include(":app-catalog")

// Core Modules
include(":core:analytics")
include(":core:common")
include(":core:network")
include(":core:database")
include(":core:persistence")
include(":core:designsystem")
include(":core:data")
include(":core:domain")
include(":core:ui")
