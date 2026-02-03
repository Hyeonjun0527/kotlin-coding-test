
fun main(args: Array<String>) {
    val t = readln().toInt()
    repeat(t) {
        val n = readln().toInt()
        val dp = MutableList(n + 4) {0}
        dp[1] = 1
        dp[2] = 2
        dp[3] = 4

        if (n <= 3) {
            println(dp[n])
            return@repeat
        }

        for (i in 4..n) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
        }
        println(dp[n])
    }
}