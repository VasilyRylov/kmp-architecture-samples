plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.commonUi)

            implementation(libs.decompose)
        }
        commonTest.dependencies {
        }
    }
}