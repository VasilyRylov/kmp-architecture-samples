import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
}

kotlin {
    androidLibrary {
        namespace = "io.github.vasilyrylov.archsample.composeApp"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
        androidResources {
            enable = true
        }
        withHostTest {
            isIncludeAndroidResources = true
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            export(projects.feature.root.rootComponent)
            export(libs.decompose)
            export(libs.essenty.lifecycle)
        }
    }

    jvm()

//    sourceSets {
//        commonMain.dependencies {
//            implementation(projects.feature.root.rootComponent)
//            implementation(projects.common.commonUi)
//            implementation(projects.common.commonData)
//
//            implementation(libs.compose.runtime)
//
//            implementation(libs.decompose)
//            implementation(libs.decompose.compose)
//            implementation(libs.kotlin.inject.runtime)
//        }
//
//        commonTest.dependencies {
//            implementation(kotlin("test"))
//            @OptIn(ExperimentalComposeLibrary::class)
//            implementation(libs.compose.uiTest)
//            implementation(libs.kotlinx.coroutines.test)
//        }
//
//        androidMain.dependencies {
//            implementation(libs.androidx.activityCompose)
//            implementation(libs.kotlinx.coroutines.android)
//        }
//
//        jvmMain.dependencies {
//            implementation(libs.compose.desktop.currentOs)
//            implementation(libs.kotlinx.coroutines.swing)
//        }
//
//        iosMain.dependencies {
//            api(projects.feature.root.rootComponent)
//            api(libs.decompose)
//            api(libs.essenty.lifecycle)
//        }
//    }
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(libs.compose.runtime)
        implementation(libs.compose.foundation)
        implementation(libs.compose.material3)
        implementation(libs.compose.ui)
        implementation(libs.compose.components.resources)
        implementation(libs.compose.uiToolingPreview)

        implementation(libs.androidx.lifecycle.viewmodelCompose)
        implementation(libs.androidx.lifecycle.runtimeCompose)

        implementation(libs.kotlin.inject.runtime)

        implementation(projects.common.commonUi)
        implementation(projects.common.commonData)
        implementation(projects.feature.root.rootComponent)

        implementation(libs.kotlinx.datetime)

        testImplementation(libs.kotlin.test)
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)

    add("kspAndroid", libs.kotlin.inject.compiler)
    add("kspJvm", libs.kotlin.inject.compiler)
    add("kspIosSimulatorArm64", libs.kotlin.inject.compiler)
    add("kspIosX64", libs.kotlin.inject.compiler)
    add("kspIosArm64", libs.kotlin.inject.compiler)
}
