package 코테.`12-9`

/*
[문제 요약(현재 우리가 맞춘 버전)]
- 정점은 1번 ~ n번까지 존재한다.
- 간선은 무방향이며, 간선목록의 각 원소는 [a, b] 형태이다.
- 시작정점에서 DFS를 수행한다.
- 인접 정점은 번호 오름차순으로 방문한다.
- 출력: DFS 방문 순서를 담은 배열
간선목록 = [[1,2], [1,3], [2,4], [3,4]]
시작정점 = 1
answer = [1, 2, 4, 3]
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - 인접 리스트를 정렬해서 방문 우선순위를 고정한다.
     * - 재귀 DFS로 깊이 우선 방문 순서를 만든다.
     *
     * [데이터 구조]
     * - graph: 인접 리스트
     * - visited: 방문 여부
     * - 결과배열: DFS 순서
     *
     * [시뮬레이션 순서]
     * <1> graph 구성 + 정렬
     * <2> 시작 정점에서 DFS 시작
     * <3> 현재 정점을 결과에 기록
     * <4> 인접 정점 중 미방문 정점으로 재귀 진행
     *
     * [주석 템플릿 규칙]
     * - [S*] : 알고리즘 실행 흐름 단계
     * - [V*] : 핵심 상태 변수
     * - 본문 진행 순서: [S0] 초기화 -> [S1..S4] 탐색 -> [S_END] 반환
     */
    fun solution(n: Int, 간선목록: Array<IntArray>, 시작정점: Int): IntArray {
        // [S0] 초기화: graph/visited/결과(V0~V2)
        val graph = MutableList(n + 1) { mutableListOf<Int>() } // [V0]
        val visited = BooleanArray(n + 1) // [V1]
        val 방문순서 = mutableListOf<Int>() // [V2]

        // [S1] 간선 반영 + 정렬
        for (간선 in 간선목록) {
            val a = 간선[0]
            val b = 간선[1]
            graph[a].add(b)
            graph[b].add(a)
        }
        for (정점 in 1..n) graph[정점].sort()

        fun dfs(현재정점: Int) {

            // [S2] 현재 정점 방문 처리
            //방문처리
            visited[현재정점] = true // [S2][V1]
            방문순서.add(현재정점) // [S2][V2]

            //인접방문, 방문검사
            // [S4] 인접 정점 재귀 확장
            for (다음정점 in graph[현재정점]) { // [S4]
                if (visited[현재정점]) continue
                dfs(다음정점)
            }
        }

        // [S3] DFS 시작점 호출
        dfs(시작정점)

        // [S_END] 결과 반환
        return 방문순서.toIntArray()
    }
}
