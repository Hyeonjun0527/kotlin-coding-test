package backjoon.치트시트.실전문제모음.greedy

// 알고리즘 분류: greedy

fun main() {
    var (n, k) = readln().split(" ").map { it.toInt() }
    val l = IntArray (n) {
        readln().toInt()
    }
    val list = l.sortedByDescending { it }
    var cnt = 0
    for (x in list) {
        if (x > k) {
            continue
        }
        cnt += k / x
        k = k % x
    }
    print(cnt)
}
