package backjoon.`20260222`

import kotlin.math.sqrt

fun main() {
    // ---------------------------
    // 입력 (문제에서 한 줄에 N개, M개 보장)
    // ---------------------------
    val n = readln().toInt()
    val a목록 = readln().split(" ").map { it.toLong() }.toMutableList()

    val m = readln().toInt()
    val b목록 = readln().split(" ").map { it.toLong() }.toMutableList()

    // 문제
    // 에라토스테네스의 체
    val 한계 = sqrt(1_000_000_000.0).toInt() + 1
    val 소수이다 = BooleanArray(한계 + 1) { true }
    val 소수들 = mutableListOf<Int>()
    for (i in 2..한계) {
        if (소수이다[i]) {
            //소수 더하기
            소수들.add(i)

            //소수제거
            var j = i + i
            while (j <= 한계) {
                소수이다[i] = false
                j += i
            }
        }
    }
    //종료

    // 문제 2
    // 한 숫자를 소인수 분해해서 map[소수] = 지수로 누적하는 함수
    fun 소인수누적(값0: Long, 지수맵: MutableMap<Long, Long>) {
        var 값 = 값0
        for (소수_ in 소수들) {
            var 소수 = 소수_.toLong()
            if (소수 * 소수 > 값) break
            if (값 % 소수 == 0L) {
                var 지수 = 0
                while (값 % 소수 == 0L) {//소인수
                    지수++
                    값 /= 소수
                }
                지수맵[소수] = (지수맵[소수] ?: 0) + 지수
            }
        }

        if (값 > 1) {
            지수맵[값] = (지수맵[값] ?: 0) + 1
        }
    }

}