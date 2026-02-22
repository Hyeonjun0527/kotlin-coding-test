package backjoon.`20260221`//  01타일

fun main(args: Array<String>) {
    val n = readln().toInt()

    if (n == 1) {
        print(1)
        return
    } else if (n == 2) {
        print(2)
        return
    }

    val dp = MutableList(n + 1) { 0 }

    dp[1] = 1
    dp[2] = 2

    for (i in 3..dp.lastIndex) {
        dp[i] = (dp[i - 1] % 15746 + dp [i - 2] % 15746) % 15746
    }
    print(dp[n] % 15746)
}