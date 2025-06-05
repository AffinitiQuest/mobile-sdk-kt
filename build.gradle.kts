buildscript {
    val agp_version by extra("8.1.2")
    val agp_version1 by extra("8.7.0")
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.7.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
    id("com.android.library") version "8.7.0" apply false
    id("com.gradleup.nmcp") version "0.0.4" apply true
    id("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false
}
