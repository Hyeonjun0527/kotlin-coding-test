package backjoon.`20260202`

/*
[문제 요약(현재 우리가 맞춘 버전)]
- before에서 하나의 연결 성분만 값을 바꿔 after를 만들 수 있는지 판단한다.
- 이 파일은 DFS 버전 구현이다.
- 출력: 가능하면 "YES", 아니면 "NO"
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - first diff cell을 시작점으로 잡는다.
     * - before에서 같은 값 연결 성분을 DFS로 마킹한다.
     * - 성분 안/밖 조건 검증으로 정답을 결정한다.
     *
     * [데이터 구조]
     * - before, after: 입력 상태
     * - visited: 성분 포함 여부
     * - 방향벡터: 상하좌우
     */
    fun solution(before: Array<IntArray>, after: Array<IntArray>): String {
        // [S0] 초기화(V0~V4)
        val xCnt = before.size
        val yCnt = if (xCnt == 0) 0 else before[0].size
        val visited = Array(xCnt) { BooleanArray(yCnt) } // [V0]
        val dx = intArrayOf(-1, 1, 0, 0) // [V1]
        val dy = intArrayOf(0, 0, -1, 1) // [V2]

        // [S1] 바뀐 칸 찾기
        var 시작x = -1 // [V3]
        var 시작y = -1 // [V4]
        loop@ for (x in 0 until xCnt) {
            for (y in 0 until yCnt) {
                if (before[x][y] == after[x][y]) continue
                시작x = x
                시작y = y
                break@loop
            }
        }

        if (시작x == -1) return "YES"

        val 원래값 = before[시작x][시작y]

        fun dfs(x: Int, y: Int) {
            visited[x][y] = true

            // [S4] 인접 칸 재귀 확장
            for (k in 0 until 4) { // [S4]
                val nx = x + dx[k]
                val ny = y + dy[k]
                if (nx !in 0 until xCnt || ny !in 0 until yCnt) continue
                if (visited[nx][ny]) continue
                if (before[nx][ny] != 원래값) continue
                dfs(nx, ny)
            }
        }

        // [S2] 연결 성분 DFS
        dfs(시작x, 시작y)

        // [S3] 검증
        val 바뀐값 = after[시작x][시작y]
        for (x in 0 until xCnt) {
            for (y in 0 until yCnt) {
                if (visited[x][y]) {
                    if (after[x][y] != 바뀐값) return "NO"
                } else {
                    if (before[x][y] != after[x][y]) return "NO"
                }
            }
        }

        // [S_END] 결과 반환
        return "YES"
    }
}
