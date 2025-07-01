import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.sahsenvar.genlib.genlibs
import com.sahsenvar.myscaffold.extensions.androidTestImplementation
import com.sahsenvar.myscaffold.extensions.coreLibraryDesugaring
import com.sahsenvar.myscaffold.extensions.gradlePropertyAsTyped
import com.sahsenvar.myscaffold.extensions.implementation
import com.sahsenvar.myscaffold.extensions.testImplementation
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

@Suppress("unused")
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        apply(plugin = genlibs.plugins.android_library)
        apply(plugin = genlibs.plugins.kotlin_android)

        extensions.configure<LibraryExtension> {
            defaultConfig {
                compileSdk = genlibs.Versions.compileSdk.toInt()
                minSdk = genlibs.Versions.minSdk.toInt()
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            resourcePrefix = path
                .split("""\W""".toRegex())
                .drop(1)
                .distinct()
                .joinToString(separator = "_")
                .lowercase()
                .plus("_")

            compileOptions {
                // Up to Java 11 APIs are available through desugaring: https://developer.android.com/studio/write/java11-minimal-support-table
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
                isCoreLibraryDesugaringEnabled = true
            }

            testOptions {
                targetSdk = genlibs.Versions.targetSdk.toInt()
                animationsDisabled = true
            }

            dependencies {
                implementation(genlibs.androidx_tracing_ktx)
                androidTestImplementation(genlibs.kotlin_test)
                testImplementation(genlibs.kotlin_test)
            }
        }

        extensions.configure<LibraryAndroidComponentsExtension> {
            beforeVariants {
                it.androidTest.enable = it.androidTest.enable
                        && project.projectDir.resolve("src/androidTest").exists()
            }
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            compilerOptions {
                jvmTarget = JvmTarget.JVM_11
                allWarningsAsErrors = providers.gradlePropertyAsTyped<Boolean>("") ?: false
                freeCompilerArgs.addAll(
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-Xconsistent-data-class-copy-visibility", //Remove this args after Phase 3(Supposedly kotlin 2.3): https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-consistent-copy-visibility/#deprecation-timeline
                )
            }

            dependencies {
                coreLibraryDesugaring(library = genlibs.android_desugarJdkLibs)
            }
        }
    }

}