package backjoon.`20260209`

fun main() {
    repeat(3) {
        val (a, b) = readln().trim().split(" ")

        val (h, m, s) = a.split(":").map { it.toInt() }
        var 시작 = h * 3600 + m * 60 + s

        val (h2, m2, s2) = b.split(":").map { it.toInt() }
        val 끝 = h2 * 3600 + m2 * 60 + s2


        val 개수 = if (시작 <= 끝) {
            끝 - 시작 + 1
        } else {
            24*60*60 - 시작 + 끝 + 1
        }

        var cnt = 0

        repeat(개수) {
            val h = 시작 / 3600
            val m = (시작 % 3600) / 60
            val s = 시작 % 60

            val clockInt = h * 10000 + m * 100 + s
            if (clockInt % 3 == 0) cnt++

            시작++
            if (시작 == 24*60*60) 시작 = 0
        }

        println(cnt)
    }
}
