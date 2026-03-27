package 코테.`12-9`

import java.util.ArrayDeque

/*
[문제 요약(현재 우리가 맞춘 버전)]
- 정점은 1번 ~ n번까지 존재한다.
- 간선은 무방향이며, 간선목록의 각 원소는 [a, b] 형태이다.
- 같은 graph에서 DFS/BFS를 수행하며 로그를 기록한다.
- 인접 정점은 번호 오름차순으로 처리한다.
- 출력: 로그 문자열 배열
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - DFS와 BFS를 각각 수행하면서 상태 변화를 로그 배열에 기록한다.
     * - 방문 순서 결과도 로그 마지막에 함께 저장한다.
     *
     * [데이터 구조]
     * - graph: 인접 리스트
     * - visited: 탐색별 방문 배열
     * - 로그배열: 단계별 텍스트 로그
     * - 큐: BFS 대기열
     *
     * [시뮬레이션 순서]
     * <1> graph 생성 및 정렬
     * <2> DFS 수행 + 로그 기록
     * <3> BFS 수행 + 로그 기록
     * <4> DFS/BFS 방문 순서 요약 로그 추가
     *
     * [주석 템플릿 규칙]
     * - [S*] : 알고리즘 실행 단계
     * - [V*] : 상태 변수
     * - 본문 진행 순서: [S0] 초기화 -> [S1..S6] 처리 -> [S_END] 반환
     */
    fun solution(n: Int, 간선목록: Array<IntArray>, 시작정점: Int): Array<String> {
        // [S0] 초기화(V0~V5)
        val graph = Array(n + 1) { mutableListOf<Int>() } // [V0]
        val 로그 = ArrayList<String>() // [V1]
        val dfs순서 = ArrayList<Int>(n) // [V4]
        val bfs순서 = ArrayList<Int>(n) // [V5]

        // [S1] graph 구성 + 정렬
        for (간선 in 간선목록) {
            val a = 간선[0]
            val b = 간선[1]
            graph[a].add(b)
            graph[b].add(a)
        }
        for (정점 in 1..n) graph[정점].sort()

        fun dfs(현재정점: Int, visited: BooleanArray) {
            if (visited[현재정점]) {
                로그.add("[DFS] $현재정점 이미 방문")
                return
            }

            // [S2] DFS 방문 처리
            visited[현재정점] = true // [S2][V2]
            dfs순서.add(현재정점) // [S2][V4]
            로그.add("[DFS] 방문 $현재정점")

            // [S4] 인접 정점 재귀 처리
            for (다음정점 in graph[현재정점]) { // [S4]
                if (!visited[다음정점]) {
                    로그.add("[DFS] $현재정점 -> $다음정점 이동")
                    dfs(다음정점, visited)
                } else {
                    로그.add("[DFS] $현재정점 -> $다음정점 스킵")
                }
            }
        }

        // [S3] DFS 시작
        run {
            val visited = BooleanArray(n + 1) // [V2]
            dfs(시작정점, visited)
        }

        // [S5] bfs() 함수 정의 + 실행
        fun bfs(시작정점: Int, visited: BooleanArray) {
            val 큐 = ArrayDeque<Int>() // [V6]
            visited[시작정점] = true // [S5][V3]
            큐.addLast(시작정점) // [S5][V6]
            로그.add("[BFS] 시작 $시작정점")

            while (큐.isNotEmpty()) {
                val 현재정점 = 큐.removeFirst()
                bfs순서.add(현재정점) // [S5][V5]
                로그.add("[BFS] 꺼냄 $현재정점")

                // [S4] BFS 인접 정점 처리
                for (다음정점 in graph[현재정점]) { // [S4]
                    if (visited[다음정점]) {
                        로그.add("[BFS] $현재정점 -> $다음정점 스킵")
                        continue
                    }
                    visited[다음정점] = true // [S4][V3]
                    큐.addLast(다음정점) // [S4][V6]
                    로그.add("[BFS] $현재정점 -> $다음정점 큐삽입")
                }
            }
        }
        run {
            val visited = BooleanArray(n + 1) // [V3]
            bfs(시작정점, visited)
        }

        // [S6] 요약 로그 기록
        로그.add("DFS 방문 순서: ${dfs순서.joinToString(" ")}")
        로그.add("BFS 방문 순서: ${bfs순서.joinToString(" ")}")

        // [S_END] 결과 반환
        return 로그.toTypedArray()
    }
}
