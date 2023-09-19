pluginManagement {
  repositories {
    mavenCentral()
    maven(url = "https://plugins.gradle.org/m2/")
    maven(url = "https://maven.google.com/")
    gradlePluginPortal()
  }

  plugins {
    id("io.github.gradle-nexus.publish-plugin") version ("1.3.0")
  }
}

rootProject.name = "sdk-java"

include(
  "module-core",
  "example"
)
