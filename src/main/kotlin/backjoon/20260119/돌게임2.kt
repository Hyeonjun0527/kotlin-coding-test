
fun main(args: Array<String>) {
    val n = readln().toInt()

    val dp = BooleanArray(n+4)
    dp[1] = false
    dp[2] = true
    dp[3] = false

    for (i in (4..n)) {
        dp[i] = !dp[i-1] || !dp[i-3]
    }

    if (dp[n]) {
        println("SK")
    } else {
        println("CY")
    }
}