/*
[문제 요약(현재 우리가 맞춘 버전)]
- 배추가 심긴 칸(1)들이 연결된 묶음마다 지렁이 1마리가 필요하다.
- 연결 기준은 상하좌우 4방향이다.
- 출력: 필요한 최소 지렁이 수(=컴포넌트 수)
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - 배추 위치를 격자에 1로 표시한다.
     * - 1을 발견할 때 DFS로 연결된 배추를 모두 0으로 바꾼다.
     * - DFS 시작 횟수가 지렁이 수다.
     *
     * [데이터 구조]
     * - graph: 배추 배치 상태
     * - 방향벡터: 상하좌우
     * - 지렁이수: 결과 카운터
     *
     * [시뮬레이션 순서]
     * <1> 격자 생성 + 배추 위치 반영
     * <2> 전체 순회
     * <3> 배추(1) 발견 시 DFS
     * <4> 컴포넌트 수 누적
    */
    fun solution(가로: Int, 세로: Int, 배추위치: Array<IntArray>): Int {
        // [S0] 초기화(V0~V5)
        val xCnt = 세로 // [V0] 행 개수
        val yCnt = 가로 // [V1] 열 개수
        val graph = Array(xCnt) { IntArray(yCnt) } // [V2]
        val dx = intArrayOf(-1, 1, 0, 0) // [V3]
        val dy = intArrayOf(0, 0, -1, 1) // [V4]
        var 지렁이수 = 0 // [V5]

        // [S1] 배추 심기
        for (좌표 in 배추위치) {
            val y = 좌표[0] // 입력 x좌표(열)
            val x = 좌표[1] // 입력 y좌표(행)
            graph[x][y] = 1
        }

        fun dfs(x: Int, y: Int) {
            graph[x][y] = 0

            // [S4] 인접 칸 확장
            for (dir in 0..3) { // [S4]
                val nx = x + dx[dir]
                val ny = y + dy[dir]
                if (nx !in 0 until xCnt || ny !in 0 until yCnt) continue
                if (graph[nx][ny] != 1) continue
                dfs(nx, ny)
            }
        }

        // [S2] 전체 순회
        for (x in 0 until xCnt) {
            for (y in 0 until yCnt) {
                if (graph[x][y] != 1) continue
                dfs(x, y)
                지렁이수++ // [S3][V5]
            }
        }

        // [S_END] 결과 반환
        return 지렁이수
    }
}
