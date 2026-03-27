package backjoon.soma2cha.DFSBFS.`20260106`
/*
[문제 요약(현재 우리가 맞춘 버전)]
- 0/1 격자에서 1은 땅, 0은 바다이다.
- 8방향(상하좌우+대각선)으로 연결된 땅 묶음을 섬으로 본다.
- 출력: 섬의 개수
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - 격자를 순회하며 1을 만날 때마다 DFS로 연결된 땅을 모두 0으로 지운다.
     * - DFS 호출 횟수가 곧 섬 개수다.
     *
     * [데이터 구조]
     * - graph: 방문 시 0으로 변경되는 지도
     * - 방향벡터: 8방향
     * - 섬개수: 결과 카운터
     *
     * [시뮬레이션 순서]
     * <1> 지도 복사
     * <2> 전체 칸 순회
     * <3> 땅(1) 발견 시 DFS 수행
     * <4> DFS 횟수 누적
     *
     * [주석 템플릿 규칙]
     * - [S*] 실행 단계
     * - [V*] 상태 변수
    */
    fun solution(지도: Array<IntArray>): Int {
        // [S0] 초기화(V0~V3)
        val xCnt = 지도.size
        val yCnt = if (xCnt == 0) 0 else 지도[0].size
        if (xCnt == 0 || yCnt == 0) return 0

        val graph = Array(xCnt) { x -> 지도[x].clone() } // [V0]
        val dx = intArrayOf(-1, -1, 0, 1, 1, 1, 0, -1) // [V1]
        val dy = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1) // [V2]
        var 섬개수 = 0 // [V3]

        fun dfs(x: Int, y: Int) {
            graph[x][y] = 0

            // [S4] 8방향 확장
            for (i in 0..7) { // [S4]
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (nx !in 0 until xCnt || ny !in 0 until yCnt) continue
                if (graph[nx][ny] != 1) continue
                dfs(nx, ny)
            }
        }

        // [S1] 전체 순회
        for (x in 0 until xCnt) {
            for (y in 0 until yCnt) {
                if (graph[x][y] != 1) continue

                // [S2] 새 섬 탐색 시작
                dfs(x, y)

                // [S3] 섬 개수 증가
                섬개수++ // [S3][V3]
            }
        }

        // [S_END] 결과 반환
        return 섬개수
    }
}
