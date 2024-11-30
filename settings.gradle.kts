pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev")
        maven("https://maven.minecraftforge.net")
        maven("https://maven.neoforged.net/releases/")
        maven("https://maven.kikugie.dev/snapshots")
        maven("https://maven.kikugie.dev/releases")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.5-beta.5"
}

stonecutter {
    centralScript = "build.gradle.kts"
    kotlinController = true
    create(rootProject) {
        versions("1.20.1", "1.20.6", "1.21.3")
        vcsVersion = "1.20.6"
        branch("fabric")
        branch("forge")
        branch("neoforge") { versions("1.20.6", "1.21.3") }
    }
}

rootProject.name = "Broken Nametags"