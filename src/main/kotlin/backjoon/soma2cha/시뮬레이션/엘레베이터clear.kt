package backjoon.soma2cha

import java.util.ArrayDeque
import java.util.TreeSet

class Solution {
    fun solution(n: Int, 요구사항목록: Array<IntArray>): IntArray {
        data class 요청(val 시간: Int, val 층: Int)

        val 요구큐 = ArrayDeque<요청>() // 미래 요청(시간순)
        for (x in 요구사항목록) 요구큐.addLast(요청(x[0], x[1]))

        val 층별대기개수 = IntArray(n + 1) // f층 대기 개수
        val 대기중인층 = TreeSet<Int>() // 대기 존재 층 집합
        val 처리층수기록 = ArrayList<Int>(요구사항목록.size) // 처리 순서

        var 현재시간 = 1 // (가정) 1초부터 시작
        var 현재층 = 1 // 시작 1층
        var 위로 = true // 시작 위

        fun 대기추가(층: Int) { // 대기 누적 + 집합 반영
            층별대기개수[층]++
            대기중인층.add(층)
        }

        fun 현재층처리() { // 현재층 대기 전부 처리
            val cnt = 층별대기개수[현재층]
            if (cnt == 0) return
            repeat(cnt) { 처리층수기록.add(현재층) }
            층별대기개수[현재층] = 0
            대기중인층.remove(현재층)
        }

        while (true) {
            if (요구큐.isEmpty() && 대기중인층.isEmpty()) break // 종료

            while (요구큐.isNotEmpty() && 요구큐.first().시간 == 현재시간) { // 현재시간 요청 반영
                val r = 요구큐.removeFirst()
                대기추가(r.층)
            }

            현재층처리() // 도착 즉시 처리

            if (대기중인층.isEmpty()) { // 대기 없으면 정지
                현재시간++
                continue
            }

            val 위에있나 = 대기중인층.higher(현재층) != null // 위쪽 대기 여부
            val 아래에있나 = 대기중인층.lower(현재층) != null // 아래쪽 대기 여부

            if (위로) {
                if (!위에있나 && 아래에있나) 위로 = false // 위에 없고 아래만 있으면 전환
            } else {
                if (!아래에있나 && 위에있나) 위로 = true // 아래에 없고 위만 있으면 전환
            }

            if (현재층 == n) 위로 = false // n층이면 아래로
            if (현재층 == 1) 위로 = true // 1층이면 위로

            현재층 += if (위로) 1 else -1 // 1층 이동
            현재시간++ // 시간 증가
        }

        return 처리층수기록.toIntArray()
    }
}