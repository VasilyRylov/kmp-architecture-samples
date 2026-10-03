import dev.iurysouza.modulegraph.Orientation
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidMultiplatformLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.modulegraph) apply true
    alias(libs.plugins.detekt)
}

dependencies {
    detektPlugins(libs.detekt.composeRules)
}

val detektSources = files(rootDir).asFileTree.matching {
    include("**/*.kt", "**/*.kts")
    exclude("**/build/**", "**/.gradle/**")
}

detekt {
    source.setFrom(detektSources)
    config.setFrom("ci/detekt/detekt.yml")
    parallel = true
}

tasks.named<Detekt>("detekt") {
    pluginClasspath.setFrom(files())
}

tasks.register<Detekt>("detektCompose") {
    description = "Runs Compose-specific detekt rules."
    group = "verification"
    source(detektSources)
    config.setFrom("ci/detekt/detekt_compose.yml")
    pluginClasspath.setFrom(configurations.detektPlugins)
    disableDefaultRuleSets = true
    parallel = true
    reports {
        xml.outputLocation.set(layout.buildDirectory.file("reports/detekt/compose.xml"))
        html.outputLocation.set(layout.buildDirectory.file("reports/detekt/compose.html"))
        txt.outputLocation.set(layout.buildDirectory.file("reports/detekt/compose.txt"))
        sarif.outputLocation.set(layout.buildDirectory.file("reports/detekt/compose.sarif"))
        md.outputLocation.set(layout.buildDirectory.file("reports/detekt/compose.md"))
    }
}

moduleGraphConfig {
    readmePath.set("./README.md")
    heading = "### Module Graph"
    orientation.set(Orientation.TOP_TO_BOTTOM)
}