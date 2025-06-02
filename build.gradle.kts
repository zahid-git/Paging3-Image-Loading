// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id ("com.google.devtools.ksp") version "1.9.23-1.0.20" apply true
}
buildscript {
    dependencies {
        classpath(libs.build.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}

tasks.withType<org.jetbrains.kotlin.gradle.internal.KaptWithoutKotlincTask>()
    .configureEach { kaptProcessJvmArgs.add("-Xmx512m") }
