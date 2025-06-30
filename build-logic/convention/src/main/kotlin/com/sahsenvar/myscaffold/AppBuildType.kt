package com.sahsenvar.myscaffold

enum class AppBuildType(val applicationIdSuffix: String? = null) {
    Debug(".debug"),
    Release,
}