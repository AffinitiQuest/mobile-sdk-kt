import java.util.Properties

pluginManagement {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}

val mavenProperties = Properties()
val mavenPropertiesFile = File(rootDir, "maven.properties")

if (mavenPropertiesFile.exists()) {
    mavenPropertiesFile.inputStream().use { input ->
        mavenProperties.load(input)
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        maven {
            url = uri("https://pkgs.dev.azure.com/affinitiquest/AffinitiQuest/_packaging/affinitiquest-dev/maven/v1")
            credentials {
                username = mavenProperties.getProperty("maven.username")
                password = mavenProperties.getProperty("maven.password")
            }
        }
    }
}

rootProject.name = "MobileSdk"
include(":example")
include(":MobileSdk")

//// Include the external build by its path
//includeBuild("/Users/iancarbone/Documents/Development/SpruceID/AQSpruce/mobile-sdk-rs/kotlin") {
////includeBuild("/Users/iancarbone/Downloads/mobile-sdk-rs-0.9.0/kotlin") {
//    dependencySubstitution {
//        // Substitute a dependency with a project in the included build
//        substitute(module("com.spruceid.mobile.sdk.rs:mobilesdkrs")).using(project(":mobilesdkrs"))
////        substitute(module("com.developed.project:mylibrary") with project(":mylibrary"))
////        substitute module("com.developed.project:mylibrary2") with project(":mylibrary2")
//    }
//}
