plugins {
    kotlin("jvm") version "2.0.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.pi3ro"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        name = "jitpack.io"
        url = uri("https://jitpack.io")
    }
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("dev.iiahmed:ModernDisguise:2.9")
    implementation("com.github.Revxrsal.Lamp:common:3.2.1")
    implementation("com.github.Revxrsal.Lamp:bukkit:3.2.1")

    compileOnly(fileTree(mapOf("dir" to "lib", "include" to listOf("PaperSpigot-1.8.8-R0.1-SNAPSHOT.jar"))))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

tasks {
    shadowJar {
        relocate("dev.iiahmed.disguise", "me.pi3ro.disguise.api.disguise")
    }
}