package backjoon.`20260313`//  정수 a를 k로 만들기

fun main(args: Array<String>) {
    val (a, k) = readln().split(" ").map { it.toInt() }
    val dp = IntArray(k + 1)
    dp[a] = 0
    for (현재값 in a + 1..k) {
        dp[현재값] = dp[현재값 - 1] + 1

        if (현재값 % 2 == 0 && 현재값/2 >= a) {
            dp[현재값] = dp[현재값 / 2] + 1
        }
    }
    print(dp[k])
}