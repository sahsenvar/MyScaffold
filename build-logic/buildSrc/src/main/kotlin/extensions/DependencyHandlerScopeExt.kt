@file:Suppress("unused")

package extensions

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(library: String){
    add("implementation", library)
}
fun DependencyHandlerScope.implementationPlatform(library: String){
    add("implementation", platform(library))
}
fun DependencyHandlerScope.api(library: String){
    add("api", library)
}
fun DependencyHandlerScope.kapt(library: String){
    add("kapt", library)
}
fun DependencyHandlerScope.ksp(library: String){
    add("ksp", library)
}
fun DependencyHandlerScope.compileOnly(library: String){
    add("compileOnly", library)
}

fun DependencyHandlerScope.coreLibraryDesugaring(library: String){
    add("coreLibraryDesugaring", library)
}

fun DependencyHandlerScope.debugImplementation(library: String){
    add("debugImplementation", library)
}

fun DependencyHandlerScope.debugImplementationPlatform(library: String){
    add("debugImplementation", platform(library))
}

fun DependencyHandlerScope.debugApi(library: String){
    add("debugApi", library)
}
fun DependencyHandlerScope.testApi(library: String){
    add("testApi", library)
}
fun DependencyHandlerScope.testImplementation(library: String){
    add("testImplementation", library)
}
fun DependencyHandlerScope.androidTestImplementation(library: String){
    add("androidTestImplementation", library)
}

fun DependencyHandlerScope.androidTestImplementationPlatform(library: String){
    add("androidTestImplementation", platform(library))
}


