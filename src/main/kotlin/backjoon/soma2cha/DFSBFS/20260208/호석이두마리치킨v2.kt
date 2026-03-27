package backjoon.`20260208`

import java.util.ArrayDeque
import kotlin.math.min

/*
[문제 요약(현재 우리가 맞춘 버전)]
- 호석이 두 마리 치킨 문제의 동일 풀이를 sequence 스타일 BFS로 구현한 버전.
- 출력: [a, b, 최소왕복합]
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - dist 테이블을 전부 만든 뒤 쌍 완전탐색으로 답을 찾는다.
     * - BFS의 이웃 처리만 sequence 체이닝으로 작성했다.
     *
     * [데이터 구조]
     * - graph, dist, 큐
     */
    fun solution(건물수: Int, 도로목록: Array<IntArray>): LongArray {
        // [S0] 초기화(V0~V3)
        val graph = Array(건물수 + 1) { mutableListOf<Int>() } // [V0]
        val dist = Array(건물수 + 1) { IntArray(건물수 + 1) { -1 } } // [V1]
        val 큐 = ArrayDeque<Int>() // [V2]

        // [S1] 간선 입력 반영
        for (도로 in 도로목록) {
            val a = 도로[0]
            val b = 도로[1]
            graph[a].add(b)
            graph[b].add(a)
        }

        fun bfs(시작: Int) {
            val d = dist[시작]
            d[시작] = 0
            큐.clear()
            큐.addLast(시작)

            while (큐.isNotEmpty()) {
                val 현재 = 큐.removeFirst()

                // [S4] 인접 노드 확장(sequence)
                graph[현재]
                    .asSequence() // [S4]
                    .filter { 다음 -> d[다음] == -1 }
                    .onEach { 다음 -> d[다음] = d[현재] + 1 }
                    .forEach { 다음 -> 큐.addLast(다음) }
            }
        }

        // [S2] all-pairs 거리 계산
        for (s in 1..건물수) bfs(s)

        // [S3] 최적 조합 탐색
        var bestA = 1
        var bestB = 2
        var bestSum = Long.MAX_VALUE

        for (a in 1..건물수) {
            for (b in a + 1..건물수) {
                var 합계 = 0L
                var 가능 = true

                for (x in 1..건물수) {
                    val da = dist[x][a]
                    val db = dist[x][b]
                    val 편도 = when {
                        da == -1 && db == -1 -> -1
                        da == -1 -> db
                        db == -1 -> da
                        else -> min(da, db)
                    }
                    if (편도 == -1) {
                        가능 = false
                        break
                    }
                    합계 += 편도 * 2L
                }

                if (!가능) continue
                if (
                    합계 < bestSum ||
                    (합계 == bestSum && a < bestA) ||
                    (합계 == bestSum && a == bestA && b < bestB)
                ) {
                    bestSum = 합계
                    bestA = a
                    bestB = b
                }
            }
        }

        // [S_END] 결과 반환
        return longArrayOf(bestA.toLong(), bestB.toLong(), bestSum)
    }
}
