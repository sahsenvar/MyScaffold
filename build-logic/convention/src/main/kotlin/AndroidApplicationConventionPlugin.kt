import com.android.build.api.dsl.ApplicationExtension
import com.sahsenvar.genlib.genlibs
import com.sahsenvar.myscaffold.AppBuildType
import com.sahsenvar.myscaffold.extensions.coreLibraryDesugaring
import com.sahsenvar.myscaffold.extensions.gradlePropertyAsTyped
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
class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        apply(plugin = genlibs.plugins.android_application)
        apply(plugin = genlibs.plugins.kotlin_android)

        extensions.configure<ApplicationExtension> {
            defaultConfig {
                compileSdk = genlibs.Versions.compileSdk.toInt()
                minSdk = genlibs.Versions.minSdk.toInt()
                targetSdk = genlibs.Versions.targetSdk.toInt()
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            compileOptions {
                // Up to Java 11 APIs are available through desugaring: https://developer.android.com/studio/write/java11-minimal-support-table
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
                isCoreLibraryDesugaringEnabled = true
            }

            buildTypes {
                release {
                    isMinifyEnabled = true
                    applicationIdSuffix = AppBuildType.Release.applicationIdSuffix
                    proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
                }
                debug {
                    isDebuggable = true
                    applicationIdSuffix = AppBuildType.Debug.applicationIdSuffix
                }
            }

            packaging {
                resources {
                    excludes.add("/META-INF/{AL2.0,LGPL2.1}") // to avoid Licence conflicts and reduce app size
                }
                jniLibs {
                    keepDebugSymbols.add("**/libandroidx.graphics.path.so") // to solve this problem to increase build speed: https://github.com/mostafaalagamy/Metrolist/issues/544
                }
            }

            testOptions {
                animationsDisabled = true
            }
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            compilerOptions {
                jvmTarget = JvmTarget.JVM_11
                allWarningsAsErrors = providers.gradlePropertyAsTyped<Boolean>("") ?: false
                freeCompilerArgs.addAll(
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi", //Enable experimental coroutines APIs, including Flow
                    "-Xconsistent-data-class-copy-visibility", //Remove this args after Phase 3(Supposedly kotlin 2.3): https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-consistent-copy-visibility/#deprecation-timeline
                )
            }

            dependencies {
                coreLibraryDesugaring(library = genlibs.android_desugarJdkLibs)
            }
        }
    }
}
