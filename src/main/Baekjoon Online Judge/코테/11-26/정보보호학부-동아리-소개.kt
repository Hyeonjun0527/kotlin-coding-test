
fun main() {
    val n = readln()
    val message = when (n) {
        "M" -> "MatKor"
        "W" -> "WiCys"
        "C" -> "CyKor"
        "A" -> "AlKor"
        "$" -> "${'$'}clear"
        else -> ""
    }
    println(message)
}