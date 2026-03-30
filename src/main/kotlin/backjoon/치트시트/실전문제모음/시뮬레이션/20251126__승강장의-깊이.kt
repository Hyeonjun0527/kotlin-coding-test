package backjoon.치트시트.실전문제모음.시뮬레이션

// 알고리즘 분류: 시뮬레이션

fun main(args: Array<String>) {
    val n = readln().toInt()
    var acc = 0
    repeat(n) {
        val(a,b) = readln().split(" ").map{it.toInt()}
        acc += (a-b)
        println(acc)
    }
}
