/*
[문제 요약(현재 우리가 맞춘 버전)]
- 1번이 루트인 트리에서 각 노드(2..n)의 부모를 구한다.
- 간선은 무방향으로 주어진다.
- 출력: 부모 배열(2번부터 n번까지)
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - 루트 1에서 DFS를 돌며 처음 방문한 경로의 직전 정점을 부모로 기록한다.
     *
     * [데이터 구조]
     * - graph: 인접 리스트
     * - visited: 재방문 방지
     * - 부모배열: 각 노드의 부모 기록
     *
     * [시뮬레이션 순서]
     * <1> graph 구성
     * <2> 루트(1) DFS 시작
     * <3> 미방문 이웃 방문 시 부모 저장
     * <4> 2..n 부모만 결과로 반환
     */
    fun solution(n: Int, 간선목록: Array<IntArray>): IntArray {
        // [S0] 초기화(V0~V2)
        val graph = Array(n + 1) { mutableListOf<Int>() } // [V0]
        val visited = BooleanArray(n + 1) // [V1]
        val 부모배열 = IntArray(n + 1) // [V2]

        // [S1] 간선 반영
        for (간선 in 간선목록) {
            val a = 간선[0]
            val b = 간선[1]
            graph[a].add(b)
            graph[b].add(a)
        }

        fun dfs(현재: Int) {
            visited[현재] = true // [S2][V1]

            // [S4] 인접 노드 순회
            for (다음 in graph[현재]) { // [S4]
                if (visited[다음]) continue
                부모배열[다음] = 현재 // [S3][V2]
                dfs(다음)
            }
        }

        // [S2] 루트 DFS 시작
        dfs(1)

        // [S_END] 결과 반환 (2..n)
        return IntArray(n - 1) { idx -> 부모배열[idx + 2] }
    }
}
