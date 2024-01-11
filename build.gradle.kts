val name: String by project
val version: String by project
val targetPaperAPI: String by project

plugins {
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

kotlin {
    jvmToolchain(17)
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    dependencies {
        /* Essentials Dependency */
        compileOnly(kotlin("stdlib")) // Kotlin Standard Library : Apache-2.0 License
        compileOnly(kotlin("reflect")) // Kotlin Reflection : Apache-2.0 License
        compileOnly("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.7.3") // Kotlin Coroutines : Apache-2.0 License
        /* Essentials Dependency */

        compileOnly(fileTree(mapOf("dir" to "libs/compileOnly", "include" to listOf("*.jar")))) // Load all jars in libs folder (Local Libraries)
        compileOnly("io.papermc.paper", "paper-api", "${targetPaperAPI}-R0.1-SNAPSHOT") // PaperMC API : MIT License

        implementation(fileTree(mapOf("dir" to "libs/implementation", "include" to listOf("*.jar")))) // Load all jars in libs folder (Local Libraries)
    }

    tasks.test {
        useJUnitPlatform()
    }
}