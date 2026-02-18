package backjoon.`20260216`

fun main() {
    val n = readln().toInt()

    val 과제목록 = mutableListOf<Pair<Long, Long>>()

    repeat(n) {
        val (d, t) = readln().split(" ").map { it.toLong() }
        과제목록.add(Pair(t, d))
    }

    과제목록.sortWith(compareByDescending { it.first })

    var cur = Long.MAX_VALUE
    var 끝날짜 = cur

    for ((t, d) in 과제목록) {
        val deadline = t
        val duration = d

        if (끝날짜 > deadline) 끝날짜 = deadline

        끝날짜 -= duration
    }

    println(끝날짜)
}
