package backjoon.치트시트.DP

fun main() {
    val n = readln().toInt()

    val dp = LongArray(n + 1).apply {
        if (n >= 1) this[1] = 1
        for (i in 2..n) {
            this[i] = this[i - 1] + this[i - 2]
        }
    }

    println(dp[n])
}
