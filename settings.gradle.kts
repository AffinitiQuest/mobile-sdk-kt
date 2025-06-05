pluginManagement {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenLocal()
        mavenCentral()
    }
}

rootProject.name = "MobileSdk"
include(":example")
include(":MobileSdk")

// Include the external build by its path
includeBuild("/Users/iancarbone/Documents/Development/SpruceID/mobile-sdk-rs/kotlin") {
//includeBuild("/Users/iancarbone/Downloads/mobile-sdk-rs-0.9.0/kotlin") {
    dependencySubstitution {
        // Substitute a dependency with a project in the included build
        substitute(module("com.spruceid.mobile.sdk.rs:mobilesdkrs")).using(project(":mobilesdkrs"))
//        substitute(module("com.developed.project:mylibrary") with project(":mylibrary"))
//        substitute module("com.developed.project:mylibrary2") with project(":mylibrary2")
    }
}
