package 코테.`12-9`

import java.util.ArrayDeque

/*
[문제 요약(현재 우리가 맞춘 버전)]
- 컴퓨터는 0번 ~ n-1번으로 번호가 붙어 있다.
- computers[i][j] == 1 이면 i와 j가 직접 연결되어 있다.
- 직접 또는 간접으로 이어진 컴퓨터 묶음을 하나의 네트워크로 본다.
- 출력: 전체 네트워크(연결 컴포넌트) 개수
n = 3
computers = [[1,1,0], [1,1,0], [0,0,1]]
answer = 2
*/

class Solution {

    /**
     * [핵심 아이디어]
     * - 방문하지 않은 컴퓨터를 시작점으로 BFS를 돌리면 해당 연결 컴포넌트를 한 번에 방문할 수 있다.
     * - 시작점을 하나 잡아 BFS가 끝날 때마다 네트워크 수를 1 증가시키면 된다.
     *
     * [데이터 구조]
     * - V0 총컴퓨터수(Int): 정점 개수 n
     * - V1 graph(Array<IntArray>): 인접 행렬
     * - V2 visited(BooleanArray): 각 컴퓨터 방문 여부
     * - V3 네트워크수(Int): 정답 카운트
     * - V4 큐(ArrayDeque<Int>): BFS 대기열
     * - V5 현재컴퓨터(Int): 큐에서 꺼낸 노드
     * - V6 다음컴퓨터(Int): 인접 후보 노드
     *
     * [탐색 순서]
     * <1> V0~V3 초기화
     * <2> 시작 컴퓨터를 0부터 n-1까지 순회
     * <3> 미방문 시작점이면 네트워크 수를 증가시키고 BFS 시작
     * <4> 큐에서 현재컴퓨터를 꺼내 모든 다음컴퓨터를 검사해 확장
     * <5> 전체 시작점 순회가 끝나면 네트워크 수 반환
     *
     * [주석 템플릿 규칙]
     * - [S*] : 알고리즘 실행 흐름 단계
     * - [V*] : 핵심 상태 변수
     * - 본문 진행 순서: [S0] 초기화 -> [S1..S5] BFS 처리 -> [S_END] 반환
     * - 상태 변수 선언/핵심 갱신 위치에는 [V*]를 함께 표기
     * - 알고리즘 핵심 분기/확장 라인에는 [S4]를 표기
     */
    fun solution(n: Int, computers: Array<IntArray>): Int {
        // [S0] 초기화: 입력/상태 변수 준비
        val 총컴퓨터수 = n // [V0] 전체 컴퓨터 수
        val graph = computers // [V1] 인접 행렬
        val visited = BooleanArray(총컴퓨터수) // [V2] 방문 여부
        var 네트워크수 = 0 // [V3] 연결 컴포넌트 개수

        // [S1] bfs() 함수 정의
        fun bfs(시작컴퓨터: Int) {
            val 큐 = ArrayDeque<Int>() // [V4] BFS 큐
            visited[시작컴퓨터] = true // [S3][V2] 시작점 방문 처리
            큐.addLast(시작컴퓨터) // [S3][V4] 시작점 큐 삽입

            while (큐.isNotEmpty()) { // [S4][V4]
                val 현재컴퓨터 = 큐.removeFirst() // [V5] 현재 확장 노드
                val 연결행 = graph[현재컴퓨터] // [V1][V5] 현재 컴퓨터의 인접 정보

                for (다음컴퓨터 in 연결행.indices) { // [V6] 인접 후보 순회
                    if (연결행[다음컴퓨터] == 0) continue // [S4][V1][V5][V6]
                    if (visited[다음컴퓨터]) continue // [S4][V2][V6]
                    visited[다음컴퓨터] = true // [S4][V2] 새 노드 방문 확정
                    큐.addLast(다음컴퓨터) // [S4][V4] 다음 탐색 대상으로 확장
                }
            }
        }

        // [S2] 모든 컴퓨터를 시작점 후보로 순회
        for (시작컴퓨터 in 0 until 총컴퓨터수) {
            if (visited[시작컴퓨터]) continue // [S2][V2]

            // [S3] 새로운 네트워크 시작: 카운트 증가 + BFS 호출
            네트워크수++ // [S3][V3] 새 연결 컴포넌트 발견
            bfs(시작컴퓨터) // [S3]

            // [S5] 현재 시작점의 연결 컴포넌트 탐색 완료
        }

        // [S_END] 결과 반환
        return 네트워크수
    }
}
