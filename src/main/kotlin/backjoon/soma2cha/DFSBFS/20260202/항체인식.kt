package backjoon.`20260202`

import java.util.ArrayDeque

/*
[문제 요약(현재 우리가 맞춘 버전)]
- before 격자에서 하나의 연결 성분(상하좌우, 같은 값)만 한 값으로 바꿔 after를 만들 수 있는지 판단한다.
- 출력: 가능하면 "YES", 아니면 "NO"
*/

class Solution {

    /**
     * [이 풀이의 핵심 아이디어]
     * - before/after가 다른 칸 하나를 시작점으로 잡는다.
     * - before에서 같은 값으로 연결된 성분을 BFS로 찾는다.
     * - 성분 안/밖 검증 규칙을 만족하는지 확인한다.
     *
     * [데이터 구조]
     * - before, after: 입력 격자
     * - visited: 바꿔야 하는 성분 표시
     * - 큐: BFS 탐색용
     *
     * [시뮬레이션 순서]
     * <1> 서로 다른 시작 칸 찾기
     * <2> 시작 칸 값으로 연결 성분 BFS
     * <3> 성분 안은 after 값이 전부 동일한지 확인
     * <4> 성분 밖은 before/after 동일한지 확인
     */
    fun solution(before: Array<IntArray>, after: Array<IntArray>): String {
        // [S0] 초기화(V0~V4)
        val xCnt = before.size
        val yCnt = if (xCnt == 0) 0 else before[0].size
        val visited = Array(xCnt) { BooleanArray(yCnt) } // [V0]
        val dx = intArrayOf(-1, 1, 0, 0) // [V1]
        val dy = intArrayOf(0, 0, -1, 1) // [V2]

        // [S1] 바뀐 칸 하나 찾기
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

        // [S2] bfs() 함수 정의
        val 원래값 = before[시작x][시작y]

        fun bfs(시작x: Int, 시작y: Int) {
            val 큐 = ArrayDeque<Pair<Int, Int>>()
            큐.addLast(시작x to 시작y)
            visited[시작x][시작y] = true

            while (큐.isNotEmpty()) {
                val (x, y) = 큐.removeFirst()

                // [S4] 4방향 확장
                for (k in 0 until 4) { // [S4]
                    val nx = x + dx[k]
                    val ny = y + dy[k]
                    if (nx !in 0 until xCnt || ny !in 0 until yCnt) continue
                    if (visited[nx][ny]) continue
                    if (before[nx][ny] != 원래값) continue
                    visited[nx][ny] = true
                    큐.addLast(nx to ny)
                }
            }
        }
        bfs(시작x, 시작y)

        // [S3] 성분 안/밖 검증
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
