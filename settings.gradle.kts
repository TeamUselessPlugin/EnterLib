rootProject.name = "EnterLib"

val libName = rootProject.name.lowercase()

include("$libName-api", "$libName-plugin")

val nms = "$libName-core"
include(nms)
file(nms).listFiles()?.filter {
    it.isDirectory && it.name.startsWith("v")
}?.forEach { file ->
    include(":$nms:${file.name}")
}