package backjoon.치트시트.실전문제모음.수학

// 알고리즘 분류: 수학

fun main() {
    val (a, b) = readln().split(" ").map { it.toLong() }

    val 시작 = minOf(a, b)
    val 끝 = maxOf(a, b)

    val 개수 = 끝 - 시작 + 1
    val 합 = 개수 * (시작 + 끝) / 2

    println(합)
}
