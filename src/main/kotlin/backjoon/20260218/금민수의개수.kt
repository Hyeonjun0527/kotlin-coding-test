package backjoon.`20260218`

fun main() {
    val (a, b) = readln().split(" ").map { it.toInt() }
    var cnt = 0

    fun run(cur: Int) {
        if (cur > b) return
        if (cur >= a) {
            cnt++
        }
        run(cur * 10 + 4)
        run(cur * 10 + 7)
    }
    run(4)
    run(7)
    print(cnt)
}