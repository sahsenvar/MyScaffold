@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.LibraryExtension
import com.sahsenvar.genlib.genlibs
import com.sahsenvar.myscaffold.extensions.androidTestImplementationPlatform
import com.sahsenvar.myscaffold.extensions.debugImplementation
import com.sahsenvar.myscaffold.extensions.debugImplementationPlatform
import com.sahsenvar.myscaffold.extensions.gradlePropertyAsTyped
import com.sahsenvar.myscaffold.extensions.implementation
import com.sahsenvar.myscaffold.extensions.implementationPlatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

@Suppress("unused")
class AndroidLibraryComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        apply(plugin = genlibs.plugins.myScaffold_android_library)
        apply(plugin = genlibs.plugins.kotlin_compose)

        extensions.configure<LibraryExtension> {
            buildFeatures.compose = true

            dependencies {
                implementationPlatform(genlibs.compose_bom)
                implementation(genlibs.compose_ui_tooling_preview)

                debugImplementationPlatform(genlibs.compose_bom)
                debugImplementation(genlibs.compose_ui_tooling)

                androidTestImplementationPlatform(genlibs.compose_bom)
            }

            testOptions {
                unitTests {
                    isIncludeAndroidResources = true
                }
            }
        }

        // make compose compiler metric analyse.(read Build-logic/README.md for more info)
        extensions.configure<ComposeCompilerGradlePluginExtension> {
            fun relativeToRootProject(dir: String) = project.provider { this }.map {
                isolated.rootProject.projectDirectory
                    .dir("build")
                    .dir(projectDir.toRelativeString(rootDir))
            }.map { it.dir(dir) }

            providers.gradlePropertyAsTyped<Boolean>("enableComposeCompilerMetrics")
                ?.let { relativeToRootProject("compose-metrics") }
                ?.let(metricsDestination::set)

            providers.gradlePropertyAsTyped<Boolean>("enableComposeCompilerReports")
                ?.let { relativeToRootProject("compose-reports") }
                ?.let(reportsDestination::set)

            stabilityConfigurationFiles
                .add(isolated.rootProject.projectDirectory.file("compose_compiler_config.conf"))
        }
    }

}