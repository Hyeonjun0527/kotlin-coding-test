package backjoon.치트시트.실전문제모음.출력

// 알고리즘 분류: 출력

fun main() {
    val line = readln()
    var r = 0L
    val mod = 20000303
    line.forEach {
        r = (r*10 + it.digitToInt()) % mod
    }
    println(r)
}
