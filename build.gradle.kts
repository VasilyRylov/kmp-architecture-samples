import dev.iurysouza.modulegraph.Orientation

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidMultiplatformLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.room).apply(false)
    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.modulegraph).apply(true)
}

moduleGraphConfig {
    readmePath.set("./README.md")
    heading = "### Module Graph"
    orientation.set(Orientation.TOP_TO_BOTTOM)
}