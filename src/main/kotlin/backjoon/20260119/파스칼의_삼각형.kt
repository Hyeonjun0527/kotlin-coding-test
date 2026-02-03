
fun main(args: Array<String>) {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val dp = MutableList(n+1){ MutableList(n+1) {0} }

    for (i in 1..n) {
        dp[i][1] = 1
        dp[i][i] = 1
    }

    for (i in 3..n) {
        for (j in 2..<i) {
            dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
        }
    }
    println(dp[n][k])
}