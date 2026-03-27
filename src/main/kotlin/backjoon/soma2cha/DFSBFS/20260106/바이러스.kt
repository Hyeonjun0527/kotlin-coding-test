/*
[문제 요약(현재 우리가 맞춘 버전)]
- 1번 컴퓨터에서 시작해 연결된 컴퓨터를 모두 감염시킨다.
- 간선은 양방향 연결이다.
- 출력: 1번을 제외한 감염 컴퓨터 수
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - DFS로 1번에서 도달 가능한 정점을 모두 방문한다.
     * - 방문한 정점 수에서 1번을 제외하면 정답이다.
     *
     * [데이터 구조]
     * - graph: 인접 리스트
     * - visited: 감염/방문 여부
     *
     * [시뮬레이션 순서]
     * <1> graph 구성
     * <2> 1번에서 DFS 시작
     * <3> 방문 수 누적
     * <4> 시작점 제외해서 반환
     *
     * [주석 템플릿 규칙]
     * - [S*] : 실행 단계
     * - [V*] : 상태 변수
     */
    fun solution(컴퓨터수: Int, 간선목록: Array<IntArray>): Int {
        // [S0] 초기화(V0~V2)
        val graph = Array(컴퓨터수 + 1) { mutableListOf<Int>() } // [V0]
        val visited = BooleanArray(컴퓨터수 + 1) // [V1]
        var 감염수 = 0 // [V2]

        // [S1] 간선 반영
        for (간선 in 간선목록) {
            val a = 간선[0]
            val b = 간선[1]
            graph[a].add(b)
            graph[b].add(a)
        }

        fun dfs(현재: Int) {
            visited[현재] = true // [S2][V1]
            감염수++ // [S2][V2]

            // [S4] 인접 정점 확장
            for (다음 in graph[현재]) { // [S4]
                if (visited[다음]) continue
                dfs(다음)
            }
        }

        // [S2] DFS 시작
        dfs(1)

        // [S3] 시작점 제외
        val 정답 = 감염수 - 1

        // [S_END] 결과 반환
        return 정답
    }
}
