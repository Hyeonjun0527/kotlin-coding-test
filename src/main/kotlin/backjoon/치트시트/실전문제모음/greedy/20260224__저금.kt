package backjoon.치트시트.실전문제모음.greedy

// 알고리즘 분류: greedy

fun main(args: Array<String>) {
    val sb = StringBuilder()
    while (true) {
        val input = try {
            readln()
        } catch (e: Exception) {
            break
        }

        var 돈 = 0.0
        var 이자 = 0.0
        var 한계 = 0.0
        if (input != "") {
            val (돈1, 이자1, 한계1) = input.split(" ").map { it.toDouble() }
            돈 = 돈1
            이자 = 이자1
            한계 = 한계1
        }

        var 년 = 0
        while (한계 >= 돈) {
            돈 *= (1.0 + 이자/100)
            년++
        }
        sb.append("${년}\n")
    }
    print(sb)
}
