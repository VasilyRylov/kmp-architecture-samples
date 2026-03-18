enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "ArchSample"

pluginManagement {
    repositories {
        google {
            content { 
              	includeGroupByRegex("com\\.android.*")
              	includeGroupByRegex("com\\.google.*")
              	includeGroupByRegex("androidx.*")
                includeGroupByRegex("android.*")
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google {
            content { 
              	includeGroupByRegex("com\\.android.*")
              	includeGroupByRegex("com\\.google.*")
              	includeGroupByRegex("androidx.*")
                includeGroupByRegex("android.*")
            }
        }
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    }
}
include(":app:androidApp")
include(":app:desktopApp")
include(":app:shared")
include(":data:database")
include(":data:preferences")
include(":common:common-ui")
include(":common:common-component")
include(":common:common-domain")
include(":common:common-data")

include(":feature:root:root-domain")
include(":feature:root:root-ui")
include(":feature:root:root-component")

include(":feature:auth:auth-domain")
include(":feature:auth:auth-ui")
include(":feature:auth:auth-component")
include(":feature:auth:auth-data")

include(":feature:todo:todo-ui")
include(":feature:todo:todo-domain")
include(":feature:todo:todo-component")
include(":feature:todo:todo-data")
