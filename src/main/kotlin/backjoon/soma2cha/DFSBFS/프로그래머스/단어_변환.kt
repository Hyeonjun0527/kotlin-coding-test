package 코테.`12-9`

import java.util.ArrayDeque

/*
[문제 요약(현재 우리가 맞춘 버전)]
- begin에서 target으로 단어를 바꾸는 최소 단계를 구한다.
- 한 번에 한 글자만 바꿀 수 있다.
- 중간 단어는 반드시 words 안에 있어야 한다.
- 변환이 불가능하면 0을 반환한다.
begin = "hit"
target = "cog"
words = ["hot", "dot", "dog", "lot", "log", "cog"]
answer = 4
*/

class Solution {

    /**
     * [핵심 아이디어]
     * - 모든 변환 간선의 비용이 1이므로 최소 단계 탐색은 BFS가 가장 직접적이다.
     * - 현재 단어에서 "한 글자 차이"인 미방문 단어들을 다음 레벨로 확장하면
     *   처음 target을 만나는 단계가 최소 변환 횟수다.
     *
     * [데이터 구조]
     * - V0 시작단어(String): begin
     * - V1 목표단어(String): target
     * - V2 graph(Array<String>): words
     * - V3 visited(BooleanArray): words 인덱스 방문 여부
     * - V4 큐(ArrayDeque<Pair<String, Int>>): (현재단어, 단계)
     * - V5 현재단어(String): 큐에서 꺼낸 단어
     * - V6 현재단계(Int): 큐에서 꺼낸 단계
     * - V7 다음단어(String): 확장 후보 단어
     * - V8 정답(Int): 기본 0, 도달 시 최소 단계로 갱신
     *
     * [시뮬레이션(또는 탐색) 순서]
     * <1> 입력/상태 변수 초기화
     * <2> 목표단어가 words에 없으면 즉시 0 반환
     * <3> begin을 단계 0으로 큐에 넣고 BFS 시작
     * <4> 큐에서 상태를 꺼내 target 도달 여부 확인
     * <5> 한 글자 차이인 미방문 단어를 큐에 확장
     * <6> 탐색 종료 후 정답 반환
     *
     * [주석 템플릿 규칙]
     * - [S*] : 알고리즘 실행 흐름 단계
     * - [V*] : 핵심 상태 변수(데이터 저장소)
     * - 본문 진행 순서: [S0] 초기화 -> [S1..S5] BFS 처리 -> [S_END] 반환
     * - 상태 변수 선언/핵심 갱신 라인에 [V*]를 표기
     * - 알고리즘 핵심 분기/확장 라인에는 [S4]를 표기
     */
    fun solution(begin: String, target: String, words: Array<String>): Int {
        // [S0] 초기화: 입력/상태 변수 준비
        val 시작단어 = begin // [V0] 시작 단어
        val 목표단어 = target // [V1] 목표 단어
        val graph = words // [V2] 변환 가능 단어들
        val visited = BooleanArray(graph.size) // [V3] words 방문 여부

        fun 한글자차이인가(a: String, b: String): Boolean {
            var 차이개수 = 0
            for (i in a.indices) {
                if (a[i] == b[i]) continue
                차이개수++
                if (차이개수 > 1) return false
            }
            return 차이개수 == 1
        }

        // [S1] 목표단어 존재 여부 검사
        if (!graph.contains(목표단어)) return 0 // [S1][V2][V1]

        // [S2] bfs() 함수 정의
        fun bfs(): Int {
            val 큐 = ArrayDeque<Pair<String, Int>>() // [V4] BFS 큐
            큐.addLast(시작단어 to 0) // [S2][V4][V0]

            // [S3] BFS 루프: 현재 상태를 꺼내 도달 여부 확인
            while (큐.isNotEmpty()) {
                val (현재단어, 현재단계) = 큐.removeFirst() // [V5][V6]

                if (현재단어 == 목표단어) return 현재단계 // [S3][V6]

                // [S4] 한 글자 차이 단어로 확장(핵심 분기/확장)
                for (i in graph.indices) { // [S4]
                    if (visited[i]) continue // [S4][V3]
                    val 다음단어 = graph[i] // [V7] 다음 후보 단어
                    if (!한글자차이인가(현재단어, 다음단어)) continue // [S4][V5][V7]
                    visited[i] = true // [S4][V3]
                    큐.addLast(다음단어 to (현재단계 + 1)) // [S4][V4][V7][V6]
                }
            }

            return 0
        }

        // [S5] 탐색 종료(도달 실패 시 정답은 0 유지)
        val 정답 = bfs() // [V8]

        // [S_END] 결과 반환
        return 정답
    }
}
