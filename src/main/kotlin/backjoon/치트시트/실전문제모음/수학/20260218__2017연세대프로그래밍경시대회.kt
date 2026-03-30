package backjoon.치트시트.실전문제모음.수학

// 알고리즘 분류: 수학

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
