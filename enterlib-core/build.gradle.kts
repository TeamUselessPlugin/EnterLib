import io.papermc.paperweight.userdev.PaperweightUserDependenciesExtension

plugins {
    id("io.papermc.paperweight.userdev") version "1.5.11" apply false
}

dependencies {
    implementation(project(":enterlib-api"))
}

subprojects {
    apply(plugin = "io.papermc.paperweight.userdev")

    dependencies {
        implementation(project(":enterlib-api"))
        implementation(project(":enterlib-core"))

        val paperweight = (this as ExtensionAware).extensions.getByName("paperweight") as PaperweightUserDependenciesExtension
        paperweight.paperDevBundle("${name.substring(1)}-R0.1-SNAPSHOT")
    }
}