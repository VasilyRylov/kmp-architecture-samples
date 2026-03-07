plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.ksp)
}

kotlin {
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.todo.todoDomain)
            implementation(projects.common.commonDomain)
            implementation(projects.common.commonData)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlin.inject.runtime)
            api(libs.uuid)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
