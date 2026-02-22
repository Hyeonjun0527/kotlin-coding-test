package backjoon.`20260218`//  2017 연세대학교 프로그래밍 경시대회

fun main() {
    val n = readln().toInt()

    var cnt = 0

    for (a in 1..n) {
        if (a % 2 == 1) continue
        for (b in 1..n) {
            val c = n - a - b
            if (c <= 0) continue
            if (a <= 0 || b <= 0) continue
            if (c >= b + 2) cnt++
        }
    }

    print(cnt)
}