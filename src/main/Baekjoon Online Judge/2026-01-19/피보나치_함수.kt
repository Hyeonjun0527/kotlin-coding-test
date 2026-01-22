
fun main(args: Array<String>) {
    val t = readln().toInt()
    repeat(t) {
        val n = readln().toInt()
        val dp = MutableList(n+2) { 0 to 0}
        dp[0] = 1 to 0
        dp[1] = 0 to 1
        if (n == 0) {
            println("${dp[0].first} ${dp[0].second}")
            return@repeat
        }
        if (n == 1) {
            println("${dp[1].first} ${dp[1].second}")
            return@repeat
        }

        for (i in 2..n) {
            dp[i] = (dp[i-1].first + dp[i-2].first) to (dp[i-1].second + dp[i-2].second)
        }
        println(dp[n].first.toString() + " " + dp[n].second.toString())
    }
}