package backjoon.`20260208`

import java.util.ArrayDeque
import kotlin.math.min

/*
[문제 요약(현재 우리가 맞춘 버전)]
- 건물 graph에서 치킨집 2곳(a,b)을 선택한다.
- 각 건물 x의 왕복 시간은 min(dist(x,a), dist(x,b)) * 2 이다.
- 전체 합이 최소가 되는 (a,b)를 찾는다.
- 동점이면 a가 작은 쌍, 그다음 b가 작은 쌍을 선택한다.
- 출력: [a, b, 최소합]
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - 모든 시작점에서 BFS를 수행해 최단거리 표(dist)를 만든다.
     * - (a,b) 모든 조합을 돌며 총 왕복 시간을 계산한다.
     *
     * [데이터 구조]
     * - graph: 인접 리스트
     * - dist[s][v]: s에서 v까지 최단 거리
     * - 큐: BFS용
     */
    fun solution(건물수: Int, 도로목록: Array<IntArray>): LongArray {
        // [S0] 초기화(V0~V4)
        val graph = Array(건물수 + 1) { mutableListOf<Int>() } // [V0]
        val dist = Array(건물수 + 1) { IntArray(건물수 + 1) { -1 } } // [V1]
        val 큐 = ArrayDeque<Int>() // [V2]

        // [S1] 간선 구성
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

                // [S4] 인접 노드 확장
                for (다음 in graph[현재]) { // [S4]
                    if (d[다음] != -1) continue
                    d[다음] = d[현재] + 1
                    큐.addLast(다음)
                }
            }
        }

        // [S2] 전체 시작점 BFS
        for (s in 1..건물수) bfs(s)

        // [S3] 최적 쌍 탐색
        var bestA = 1 // [V3]
        var bestB = 2 // [V4]
        var bestSum = Long.MAX_VALUE // [V5]

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
