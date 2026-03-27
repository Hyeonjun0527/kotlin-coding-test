package 코테.`12-9`

import java.util.ArrayDeque

/*
[문제 요약(현재 우리가 맞춘 버전)]
- 정점은 1번 ~ n번까지 존재한다.
- 간선은 무방향이며, 간선목록의 각 원소는 [a, b] 형태이다.
- 시작정점에서 BFS를 수행한다.
- 인접 정점은 번호 오름차순으로 방문한다.
- 출력: BFS 방문 순서를 담은 배열
간선목록 = [[1,2], [1,3], [2,4], [3,4]]
시작정점 = 1
answer = [1, 2, 3, 4]
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - 인접 리스트를 만든 뒤 각 정점 리스트를 오름차순 정렬한다.
     * - 큐(ArrayDeque)로 너비 우선 탐색을 수행한다.
     * - 큐에 넣는 시점에 방문 처리해서 중복 삽입을 막는다.
     *
     * [데이터 구조]
     * - graph: 정점별 인접 정점 목록
     * - visited: 방문 여부
     * - 큐: BFS 대기열
     * - 결과배열: 방문 순서
     *
     * [시뮬레이션 순서]
     * <1> graph 구성 + 정렬
     * <2> 시작정점을 큐에 삽입하고 방문 처리
     * <3> 큐에서 정점을 꺼내 결과에 기록
     * <4> 인접 정점을 순회하며 미방문 정점을 큐에 삽입
     * <5> 큐가 빌 때까지 반복
     *
     * [주석 템플릿 규칙]
     * - [S*] : 알고리즘 실행 흐름 단계
     * - [V*] : 핵심 상태 변수(데이터 저장소)
     * - 본문 진행 순서: [S0] 초기화 -> [S1..S5] 탐색 -> [S_END] 반환
     */
    fun solution(n: Int, 간선목록: Array<IntArray>, 시작정점: Int): IntArray {
        // [S0] 초기화: graph/visited(V0~V1)
        val graph = Array(n + 1) { mutableListOf<Int>() } // [V0] 인접 리스트
        val visited = BooleanArray(n + 1) // [V1] 방문 여부

        // [S1] 간선 반영 + 정렬
        for (간선 in 간선목록) {
            val a = 간선[0]
            val b = 간선[1]
            graph[a].add(b)
            graph[b].add(a)
        }
        for (정점 in 1..n) graph[정점].sort()

        // [S2] bfs() 함수 정의
        fun bfs(시작정점: Int): IntArray {
            val 큐 = ArrayDeque<Int>() // [V2] BFS 큐
            val 방문순서 = ArrayList<Int>(n) // [V3] 결과

            visited[시작정점] = true // [S2][V1]
            큐.addLast(시작정점) // [S2][V2]

            // [S3] BFS 루프 시작
            while (큐.isNotEmpty()) {
                val 현재정점 = 큐.removeFirst()
                방문순서.add(현재정점) // [S3][V3]

                // [S4] 인접 정점 처리
                for (다음정점 in graph[현재정점]) { // [S4]
                    if (visited[다음정점]) continue
                    visited[다음정점] = true // [S4][V1]
                    큐.addLast(다음정점) // [S4][V2]
                }
            }

            // [S5] 탐색 종료
            return 방문순서.toIntArray()
        }

        // [S_END] 결과 반환
        return bfs(시작정점)
    }
}
