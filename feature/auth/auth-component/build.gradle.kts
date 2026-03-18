import com.google.devtools.ksp.gradle.KspAATask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.ksp)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    jvm()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        }

        commonMain.dependencies {
            implementation(projects.feature.auth.authUi)
            implementation(projects.feature.auth.authDomain)
            implementation(projects.feature.auth.authData)

            implementation(projects.common.commonComponent)
            implementation(projects.common.commonUi)
            implementation(projects.common.commonDomain)
            implementation(projects.common.commonData)

            implementation(libs.decompose)
            implementation(libs.kotlin.inject.runtime)

            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.components.resources)
        }
    }
}

dependencies {
    kspCommonMainMetadata(libs.kotlin.inject.compiler)
}

tasks.withType<KspAATask>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
