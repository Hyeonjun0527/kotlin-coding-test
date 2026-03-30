package backjoon.치트시트.실전문제모음.DP

// 알고리즘 분류: DP

fun main(args: Array<String>) {
    val n = readln().toInt()
    val dp = MutableList(n + 1) { 0L }
    dp[0] = 1L
    if (n >= 1) dp[1] = 1L
    for (현재값 in 2..n) {
        var temp = 0L
        for (왼쪽값 in 0 until 현재값) {
            val 오른쪽값 = 현재값 - 1 - 왼쪽값
            temp += dp[왼쪽값] * dp[오른쪽값]//0~n-1
//            println("temp : $temp, i : ${i} j : ${j}")
        }
        dp[현재값] = temp
//        println("dp[$i] : ${dp[i]}")
    }
    print(dp[n])
}
//intarray는 0으로 초기화 되어잇음. array<Int>는 0으로 초기화 되어잇나?
//array<Int>는 어케쓰지?
