package backjoon.치트시트.실전문제모음.정렬

// 알고리즘 분류: 정렬

fun main(args: Array<String>) {
    val (n,k) = readln().split(" ").map { it.toInt() }
    val 배열 = readln().split(" ").map { it.toInt() }.toMutableList()
    var 교환횟수 = 0

    for (마지막 in n-1 downTo 1) {
        var 최대값위치 = 0
        for (i in 1..마지막) {
            if (배열[i] > 배열[최대값위치]) {
                최대값위치 = i
            }
        }

        if (최대값위치 != 마지막) {
            배열[마지막] = 배열[최대값위치].also { 배열[최대값위치] = 배열[마지막] }
            교환횟수++
            if (교환횟수 == k) {
                println(배열.joinToString(" "))
                return
            }
        }
    }
    println(-1)
}
