fun main() {
    val n = readln().toInt()

    // -1: 아직 계산 안 함, 0: false, 1: true
    val dp = IntArray(n + 4) { -1 }

    fun solve(i: Int): Boolean {
        if (dp[i] != -1) return dp[i] == 1 // 기억했으면 사용하는 것.

        val res = when (i) {
            1 -> false
            2 -> true
            3 -> false
            else -> (!solve(i - 1)) || (!solve(i - 3))
        }

        dp[i] = if (res) 1 else 0
        return res
    }

    println(if (solve(n)) "SK" else "CY")
}
