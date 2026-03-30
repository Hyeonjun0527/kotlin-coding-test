package backjoon.치트시트.실전문제모음.greedy

// 알고리즘 분류: greedy

fun main() {
    val n = readln().toInt()
    for (five in n/5 downTo 0) {
        val rest = n - 5 * five
        if (rest % 3 == 0) {
            println(five + rest / 3)
            return
        }
    }
    println(-1)
}
