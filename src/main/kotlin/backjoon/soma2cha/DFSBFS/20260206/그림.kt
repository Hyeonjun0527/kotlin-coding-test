package backjoon.`20260206`

/*
[문제 요약(현재 우리가 맞춘 버전)]
- 0/1 격자에서 1로 이루어진 연결 요소(상하좌우)를 그림으로 본다.
- 출력: [그림개수, 가장큰그림넓이]
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - 미방문 1을 발견할 때 DFS로 그림 넓이를 계산한다.
     * - DFS 호출 횟수는 그림 개수, 반환 최대값은 최대 넓이이다.
     *
     * [데이터 구조]
     * - graph: 입력 격자
     * - visited: 방문 여부
     * - 방향벡터: 상하좌우
     */
    fun solution(그림판: Array<IntArray>): IntArray {
        // [S0] 초기화(V0~V5)
        val xCnt = 그림판.size
        val yCnt = if (xCnt == 0) 0 else 그림판[0].size
        if (xCnt == 0 || yCnt == 0) return intArrayOf(0, 0)

        val graph = Array(xCnt) { x -> 그림판[x].clone() } // [V0]
        val visited = Array(xCnt) { BooleanArray(yCnt) } // [V1]
        val dx = intArrayOf(-1, 1, 0, 0) // [V2]
        val dy = intArrayOf(0, 0, -1, 1) // [V3]
        var 최대넓이 = 0 // [V4]
        var 그림개수 = 0 // [V5]

        fun dfs(x: Int, y: Int): Int {
            visited[x][y] = true
            var 넓이 = 1

            // [S4] 인접 칸 확장
            for (k in 0..3) { // [S4]
                val nx = x + dx[k]
                val ny = y + dy[k]
                if (nx !in 0 until xCnt || ny !in 0 until yCnt) continue
                if (visited[nx][ny]) continue
                if (graph[nx][ny] != 1) continue
                넓이 += dfs(nx, ny)
            }
            return 넓이
        }

        // [S1] 전체 순회
        for (x in 0 until xCnt) {
            for (y in 0 until yCnt) {
                if (graph[x][y] != 1 || visited[x][y]) continue
                그림개수++ // [S2][V5]
                val 넓이 = dfs(x, y) // [S3]
                최대넓이 = maxOf(최대넓이, 넓이) // [S3][V4]
            }
        }

        // [S_END] 결과 반환
        return intArrayOf(그림개수, 최대넓이)
    }
}
