plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.auth.authDomain)
            implementation(projects.common.commonDomain)
            implementation(projects.common.commonData)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlin.inject.runtime)
            api(libs.uuid)
        }
    }
}
