package backjoon.치트시트.실전문제모음.출력

// 알고리즘 분류: 출력

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
