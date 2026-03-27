import java.util.ArrayDeque

/*
[문제 요약(현재 우리가 맞춘 버전)]
- 무방향 graph와 시작 정점이 주어진다.
- DFS 방문 순서와 BFS 방문 순서를 각각 구한다.
- 인접 정점은 번호 오름차순으로 방문한다.
- 출력: [DFS순서, BFS순서]
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - 같은 graph를 DFS/BFS에 각각 사용한다.
     * - 인접 리스트 정렬로 방문 우선순위를 고정한다.
     *
     * [데이터 구조]
     * - graph: 인접 리스트
     * - visited: 탐색별 방문 배열
     * - dfs순서, bfs순서: 결과 저장
     * - 큐: BFS 대기열
     *
     * [시뮬레이션 순서]
     * <1> graph 구성 + 정렬
     * <2> DFS 수행
     * <3> BFS 수행
     * <4> 두 결과 반환
     */
    fun solution(n: Int, 간선목록: Array<IntArray>, 시작정점: Int): Array<IntArray> {
        // [S0] 초기화(V0~V5)
        val graph = Array(n + 1) { mutableListOf<Int>() } // [V0]
        val dfs순서 = ArrayList<Int>(n) // [V3]
        val bfs순서 = ArrayList<Int>(n) // [V4]

        // [S1] graph 구성 + 정렬
        for (간선 in 간선목록) {
            val a = 간선[0]
            val b = 간선[1]
            graph[a].add(b)
            graph[b].add(a)
        }
        for (정점 in 1..n) graph[정점].sort()

        fun dfs(현재: Int, visited: BooleanArray) {
            visited[현재] = true // [S2][V1]
            dfs순서.add(현재) // [S2][V3]

            // [S4] DFS 인접 확장
            for (다음 in graph[현재]) { // [S4]
                if (visited[다음]) continue
                dfs(다음, visited)
            }
        }

        // [S2] DFS 수행
        run {
            val visited = BooleanArray(n + 1) // [V1]
            dfs(시작정점, visited)
        }

        // [S3] bfs() 함수 정의 + 실행
        fun bfs(시작정점: Int, visited: BooleanArray) {
            val 큐 = ArrayDeque<Int>() // [V5]
            visited[시작정점] = true // [S3][V2]
            큐.addLast(시작정점) // [S3][V5]

            while (큐.isNotEmpty()) {
                val 현재 = 큐.removeFirst()
                bfs순서.add(현재) // [S3][V4]

                // [S4] BFS 인접 확장
                for (다음 in graph[현재]) { // [S4]
                    if (visited[다음]) continue
                    visited[다음] = true // [S4][V2]
                    큐.addLast(다음) // [S4][V5]
                }
            }
        }
        run {
            val visited = BooleanArray(n + 1) // [V2]
            bfs(시작정점, visited)
        }

        // [S_END] 결과 반환
        return arrayOf(dfs순서.toIntArray(), bfs순서.toIntArray())
    }
}
