package com.sahsenvar.myscaffold.extensions

import org.gradle.api.provider.ProviderFactory

inline fun <reified T> ProviderFactory.gradlePropertyAsTyped(name: String): T? {
    return gradleProperty(name).map {
        when (T::class) {
            Boolean::class -> it.toBoolean()
            Int::class -> it.toInt()
            Long::class -> it.toLong()
            Float::class -> it.toFloat()
            Double::class -> it.toDouble()
            else -> it
        }
    }.orNull as T?
}
