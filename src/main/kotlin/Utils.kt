import java.io.File

fun readInputText(name: String) = File("src/main/kotlin/data", "$name.txt").readText()

fun readInputLines(name: String) = File("src/main/kotlin/data", "$name.txt").readLines()