import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("org.jetbrains.dokka") version "1.9.10"
}

dependencies {
    implementation("dev.jorel", "commandapi-bukkit-core", "9.3.0") // CommandAPI : MIT License
    implementation("io.github.monun", "heartbeat-coroutines", "0.0.5") {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-core")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
    } // Heartbeat Coroutine : GPL-3.0 License
    implementation("io.github.monun", "invfx-api", "3.3.2") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-reflect")
    } // InvFX API : GPL-3.0 License
}

tasks {
    register<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    create<Jar>("dokkaJar") {
        archiveClassifier.set("javadoc")
        dependsOn("dokkaHtml")

        from("${layout.buildDirectory}/dokka/html") {
            into("**")
        }
    }

    register<ShadowJar>("publishJar") {
        archiveBaseName.set(project.name)
        archiveVersion.set("${project.version}")
        archiveClassifier.set("all")
        from(sourceSets["main"].output, "/${rootDir}/LICENSE", "/${rootDir}/README.MD")

        configurations = listOf(project.configurations.runtimeClasspath.get()) // Add all dependencies to the jar
    }
}