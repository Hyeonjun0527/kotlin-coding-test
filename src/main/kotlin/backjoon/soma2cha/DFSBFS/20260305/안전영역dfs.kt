package backjoon.soma2cha.DFSBFS.`20260305` // 안전 영역

class Solution {
    fun solution(높이정보: Array<IntArray>): Int {
        val yCnt = 높이정보.size
        val xCnt = 높이정보[0].size

        val 높이지도 = MutableList(yCnt) { y ->
            MutableList(xCnt) {x -> 높이정보[y][x]}
        }

        val dx = mutableListOf(0, -1, 0, 1)
        val dy = mutableListOf(-1, 0, 1, 0)

        var 최대높이 = 0
        for (y in 0 until yCnt) {
            for (x in 0 until xCnt) {
                if (높이지도[y][x] > 최대높이) {
                    최대높이 = 높이지도[y][x]
                }
            }
        }

        var 최대안전구역수 = 0

        for (물높이 in 0..최대높이) {
            val 잠김목록 = MutableList(yCnt) { BooleanArray(xCnt) }
            val 방문목록 = MutableList(yCnt) { BooleanArray(xCnt) }

            for (y in 0 until yCnt) {
                for (x in 0 until xCnt) {
                    if (높이지도[y][x] <= 물높이) {
                        잠김목록[y][x] = true
                    }
                }
            }

            fun dfs(x: Int, y: Int) {
                방문목록[y][x] = true

                for (방향 in 0 until 4) {
                    val nx = x + dx[방향]
                    val ny = y + dy[방향]

                    if (nx !in 0 until xCnt || ny !in 0 until yCnt) continue
                    if (방문목록[ny][nx]) continue
                    if (잠김목록[ny][nx]) continue

                    dfs(nx, ny)
                }
            }

            var 안전구역수 = 0
            for (y in 0 until yCnt) {
                for (x in 0 until xCnt) {
                    if (방문목록[y][x]) continue
                    if (잠김목록[y][x]) continue
                    dfs(x, y)
                    안전구역수++
                }
            }

            if (안전구역수 > 최대안전구역수) {
                최대안전구역수 = 안전구역수
            }
        }

        return 최대안전구역수
    }
}
