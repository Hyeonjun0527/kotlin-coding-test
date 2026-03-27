package 코테.`12-14`

import java.util.ArrayDeque
import kotlin.math.roundToLong
import kotlin.random.Random

/*
[문제 요약(현재 우리가 맞춘 버전)]
- 랜덤 무방향 graph를 생성한다.
- BFS 구현 2가지(chain 스타일 vs loop 스타일)의 실행시간을 비교한다.
- warmup 후 trials번 측정한다.
- 출력: [chain평균ms, loop평균ms] 배열
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - 동일 graph에서 BFS 구현만 바꿔 성능을 비교한다.
     * - warmup에서 방문 수 일치 여부를 먼저 검증한다.
     * - 실측은 nanoTime으로 하고 ms 단위로 환산한다.
     *
     * [데이터 구조]
     * - graph: 인접 리스트
     * - visited: BFS 방문 상태
     * - chain시간, loop시간: 시행별 측정값
     *
     * [시뮬레이션 순서]
     * <1> 랜덤 graph 생성
     * <2> warmup에서 두 구현 동작 일치 검증
     * <3> trials 측정으로 시간 수집
     * <4> 평균값 계산 후 반환
     *
     * [주석 템플릿 규칙]
     * - [S*] : 알고리즘 실행 흐름 단계
     * - [V*] : 핵심 상태 변수
     */
    fun solution(
        정점수: Int = 100_000,
        간선수: Int = 10_000_000,
        시작정점: Int = 1,
        워밍업횟수: Int = 3,
        측정횟수: Int = 5,
        시드: Int = 42,
    ): LongArray {
        // [S0] 초기화(V0~V3)
        val 랜덤 = Random(시드)
        val graphTmp = Array(정점수 + 1) { mutableListOf<Int>() }

        // [S1] 랜덤 graph 생성
        repeat(간선수) {
            var a = 랜덤.nextInt(1, 정점수 + 1)
            var b = 랜덤.nextInt(1, 정점수 + 1)
            if (a == b) b = if (b < 정점수) b + 1 else 1
            graphTmp[a].add(b)
            graphTmp[b].add(a)
        }
        val graph = graphTmp.map { it.toList() } // [V0]

        fun 방문수세기(visited: BooleanArray): Int {
            var 합계 = 0
            for (i in 1..정점수) if (visited[i]) 합계++
            return 합계
        }

        // [S2] warmup 검증
        repeat(워밍업횟수) {
            val visited = BooleanArray(정점수 + 1)
            bfsChain(시작정점, graph, visited)
            val chain방문수 = 방문수세기(visited)
            visited.fill(false)
            bfsLoop(시작정점, graph, visited)
            val loop방문수 = 방문수세기(visited)
            if (chain방문수 != loop방문수) {
                return longArrayOf(-1L, -1L)
            }
        }

        // [S3] 성능 측정 배열 준비
        val chain시간 = LongArray(측정횟수) // [V1]
        val loop시간 = LongArray(측정횟수) // [V2]

        // [S4] 각 구현 측정
        repeat(측정횟수) { idx -> // [S4]
            run {
                val visited = BooleanArray(정점수 + 1)
                val 시작 = System.nanoTime()
                bfsChain(시작정점, graph, visited)
                val 종료 = System.nanoTime()
                chain시간[idx] = (종료 - 시작) / 1_000_000
            }
            run {
                val visited = BooleanArray(정점수 + 1)
                val 시작 = System.nanoTime()
                bfsLoop(시작정점, graph, visited)
                val 종료 = System.nanoTime()
                loop시간[idx] = (종료 - 시작) / 1_000_000
            }
        }

        // [S5] 평균 계산
        val chain평균 = chain시간.average().roundToLong()
        val loop평균 = loop시간.average().roundToLong()

        // [S_END] 결과 반환
        return longArrayOf(chain평균, loop평균)
    }

    private fun bfsChain(시작정점: Int, graph: List<List<Int>>, visited: BooleanArray) {
        val 큐 = ArrayDeque<Int>()
        큐.addLast(시작정점)
        visited[시작정점] = true

        while (큐.isNotEmpty()) {
            val 현재정점 = 큐.removeFirst()
            graph[현재정점]
                .asSequence()
                .filter { !visited[it] }
                .onEach { visited[it] = true }
                .forEach { 큐.addLast(it) }
        }
    }

    private fun bfsLoop(시작정점: Int, graph: List<List<Int>>, visited: BooleanArray) {
        val 큐 = ArrayDeque<Int>()
        큐.addLast(시작정점)
        visited[시작정점] = true

        while (큐.isNotEmpty()) {
            val 현재정점 = 큐.removeFirst()
            for (다음정점 in graph[현재정점]) {
                if (visited[다음정점]) continue
                visited[다음정점] = true
                큐.addLast(다음정점)
            }
        }
    }
}
