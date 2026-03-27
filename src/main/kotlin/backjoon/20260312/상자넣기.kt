package backjoon.`20260312`//  상자넣기

fun main(args: Array<String>) {
    val n = readln().toInt()

    val list = readln().split(" ").map { it.toInt() }

    var 최대 = 0
    for (i in 0 until list.size) {
        val 기준 = list[i]
        var cnt = 0
        for (j in 0 until i) {
            if (list[j] < 기준) {
                cnt++
            }
        }
        if (최대 < cnt) {
            최대 = cnt
        }
    }
    println(최대 + 1)
}