package backjoon.한국어

fun main(args: Array<String>) {
    // 1. 여기서 자르기를 호출하려면
    val (n, m) = 한줄_읽기().자르기(" ").변환하기 { it.긴_숫자로() }

    val 결과 = 한줄_읽기().자르기(" ").변환하기 { it.긴_숫자로() }
    val 답 = 결과.누적하기(1L) { 현재값, 이번숫자 -> ((현재값 % m) * (이번숫자 % m)) % m }
    한줄_출력(답)

    val board = Array(3) { IntArray(4) }
    for (row in board) {
        println(row.joinToString())
    }

    val arr = IntArray(5) { it + 1 }  // [1,2,3,4,5]

    arr.sum()         // Int
    arr.average()     // Double
    arr.maxOrNull()
    arr.minOrNull()
    arr.sorted()      // List<Int>

}

