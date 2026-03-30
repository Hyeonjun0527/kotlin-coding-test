package backjoon.치트시트.실전문제모음.greedy

// 알고리즘 분류: greedy

fun main(args: Array<String>) {

    val n  = readln().toInt()
    val list = readln().split(" ").map { it.toInt() }.sorted()

    var sum = 0
    var 답 = 0
    for (it in list) {
        sum += it
        답 += sum
    }
    println(답)

}
