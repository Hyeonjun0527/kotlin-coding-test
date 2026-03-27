package backjoon.`20260320`

fun main() {
    val n = readln().toInt()
    val 나머지 = 1_000_000_000L
    var dp1 = LongArray(0)
    var dp2 = LongArray(0)
    if (n < 0) {
        val 절댓값 = -n
        dp1 = LongArray(절댓값 + 1)
        dp1[1] = 1
        for (i in 2..절댓값) {
            dp1[i] = (-dp1[i - 1] + dp1[i - 2]) % 나머지
        }
    } else if (n == 0) {

    } else {
        dp2 = LongArray(n + 1)
        dp2[1] = 1
        for (i in 2..n) {
            dp2[i] = (dp2[i-1] + dp2[i-2]) % 나머지
        }
    }

    if (n < 0) {
        if (dp1[-n] > 0) {
            println(1)
        } else {
            println(-1)
        }
        println(if(dp1[-n] < 0) -dp1[-n] else dp1[-n])
    } else if (n == 0) {
        println(0)
        println(0)
    } else {
        println(1)
        println(dp2[n])
    }





}