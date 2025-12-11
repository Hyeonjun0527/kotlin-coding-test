fun main() {
    // -----------------------------
    // 1) 생 재귀 (메모이제이션 없음)
    // -----------------------------
    fun fibRaw(n: Int): Long {
        if (n <= 1) return 1
        return fibRaw(n - 1) + fibRaw(n - 2)
    }

    // -----------------------------
    // 2) 메모이제이션 재귀
    // -----------------------------
    val memo = LongArray(101) { 0 }
//    val memo = MutableList(101) { 0L }
//    val memo mutableListOf<Long>() 하면 바로 memo[n]을 쓸 수가 없음.동적크기리스트고 add하기전엔 size 0임.

    fun fibMemo(n: Int): Long {
        if (n <= 1) return 1
        if (memo[n] > 0) return memo[n]   // 이미 계산한 값이면 바로 반환
        memo[n] = fibMemo(n - 1) + fibMemo(n - 2)
        return memo[n]
    }

    val target = 40 // 생 재귀는 40 이상 가면 터짐 → 100 하면 죽음

    println("=== 생 재귀 결과 ===")
    val rawStart = System.currentTimeMillis()
    val rawResult = fibRaw(target)
    val rawEnd = System.currentTimeMillis()
    println("fibRaw($target) = $rawResult, 시간 = ${rawEnd - rawStart}ms\n")

    println("=== 메모이제이션 결과 ===")
    val memoStart = System.currentTimeMillis()
    val memoResult = fibMemo(target)
    val memoEnd = System.currentTimeMillis()
    println("fibMemo($target) = $memoResult, 시간 = ${memoEnd - memoStart}ms\n")
}
