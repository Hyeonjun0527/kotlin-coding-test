package backjoon.치트시트.실전문제모음.DP

// 알고리즘 분류: DP

fun main(args: Array<String>) {
    val n = readln().toInt()
    val dp = BooleanArray(n + 4)
    dp[1] = true
    dp[2] = false
    dp[3] = true

    for (i in (4..n)) {
        dp[i] = !dp[i-1] || !dp[i-3]
    }

    if (dp[n]) {
        println("SK")
    } else {
        println("CY")
    }
}
