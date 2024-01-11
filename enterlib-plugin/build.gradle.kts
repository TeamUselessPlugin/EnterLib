import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    implementation(project(":enterlib-core"))
}

tasks {
    processResources {
        filteringCharset = "UTF-8"

        filesMatching("**/*.yml") {
            expand(project.properties)
        }
    }

    project.delete(
        file("build/resources")
    )

    register<ShadowJar>("paperJar") {
        archiveBaseName.set(project.name)
        archiveVersion.set("$version")
        archiveClassifier.set("")
        from(sourceSets["main"].output, "LICENSE", "README.MD")

        configurations = listOf(project.configurations.runtimeClasspath.get()) // Add all dependencies to the jar

        doLast {
            copy {
                val archiveFileName = "${project.name}-dev.jar"

                from(archiveFile)
                rename { archiveFileName }

                val newPluginFileLocation = File("\\\\192.168.123.107\\Users\\User\\Desktop\\DEV\\plugins") // DevServer
//                val newPluginFileLocation = File(rootDir, ".dev/plugins") // Local

                if (File(newPluginFileLocation, archiveFileName).exists()) {
                    into(File(newPluginFileLocation, "update"))
                    File(newPluginFileLocation, "update/RELOAD").delete()
                } else {
                    into(newPluginFileLocation)
                }
            }
        }
    }
}