
fun main(args: Array<String>) {
    val n = readln().toInt()
    val dp = LongArray(n + 1)
    dp[1] = 4L

    if (n == 1) {
        println(dp[1])
        return
    }

    val fibo = LongArray(n + 1)
    fibo[1] = 1L
    fibo[2] = 1L
    for (i in 3 until n+1) {
        fibo[i] = fibo[i-1] + fibo[i-2]
    }
    for (i in 2 until n+1) {
        dp[i] = dp[i-1] + 2 * fibo[i]
//        println("가자")
//        println(dp[i-1])
//        println(fibo[i-1])
//        println(dp[i])
    }
    println(dp[n])
}
//intArray는 출력이 어려움.
//왜 ArrayIndexOutOfBounds가 나오지 이코드