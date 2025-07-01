import com.sahsenvar.genlib.genlibs
import com.sahsenvar.myscaffold.extensions.gradlePropertyAsTyped
import com.sahsenvar.myscaffold.extensions.testImplementation
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class JvmLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        apply(plugin = genlibs.plugins.jetbrains_kotlin_jvm)

        extensions.configure<JavaPluginExtension> {
            // Up to Java 11 APIs are available through desugaring
            // https://developer.android.com/studio/write/java11-minimal-support-table
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        extensions.configure<KotlinJvmProjectExtension>(){
            compilerOptions{
                jvmTarget = JvmTarget.JVM_11
                allWarningsAsErrors = providers.gradlePropertyAsTyped<Boolean>("warningsAsErrors") ?: false
                freeCompilerArgs.addAll(
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-Xconsistent-data-class-copy-visibility", //Remove this args after Phase 3(Supposedly kotlin 2.3): https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-consistent-copy-visibility/#deprecation-timeline
                )
            }
        }

        dependencies{
            testImplementation(genlibs.kotlin_test)
        }
    }

}