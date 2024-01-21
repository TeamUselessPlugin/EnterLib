import io.papermc.paperweight.userdev.PaperweightUserDependenciesExtension

val projectName = project.name

plugins {
    id("io.papermc.paperweight.userdev") version "1.5.11" apply false
}

dependencies {
    implementation(project(":${projectName.substringBeforeLast('-')}-api"))
}

subprojects {
    apply(plugin = "io.papermc.paperweight.userdev")

    dependencies {
        implementation(project(":${projectName.substringBeforeLast('-')}-api"))
        implementation(project(":${projectName.substringBeforeLast('-')}-core"))

        val paperweight = (this as ExtensionAware).extensions.getByName("paperweight") as PaperweightUserDependenciesExtension
        paperweight.paperDevBundle("${name.substring(1)}-R0.1-SNAPSHOT")
    }
}