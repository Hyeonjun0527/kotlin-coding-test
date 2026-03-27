package backjoon.soma2cha.DFSBFS.`20260305`

/**
 * [문제] 적록색약 (BOJ 10026)
 *
 * N x N 그림이 주어지고, 각 칸의 색은 R/G/B 중 하나다.
 * 상하좌우로 인접하면서 "같은 색"이면 같은 구역이다.
 *
 * - 일반 시야: R, G, B를 모두 서로 다른 색으로 본다.
 * - 적록색약: R과 G를 같은 색으로 본다. (B만 별도)
 *
 * 해야 할 일:
 * 1) 일반 시야 기준 구역 수
 * 2) 적록색약 기준 구역 수
 * 를 구해서 반환한다.
 *
 * 핵심 아이디어:
 * - 모든 칸을 순회하면서, 아직 방문하지 않은 칸이면 DFS 시작
 * - DFS 한 번이 구역 하나를 전부 방문하므로 시작할 때마다 구역 수 +1
 * - 이 과정을
 *   (a) 일반 방문목록으로 1번,
 *   (b) 색약 방문목록으로 1번
 *   총 2번 수행한다.
 *
 * 색약 모드 구역 판정:
 * - 시작색이 B면 다음칸도 B여야만 같은 구역
 * - 시작색이 R 또는 G면 다음칸이 B가 아니면(=R/G) 같은 구역

 * 예시
 * | N | picture | return |
 * |---|---|---|
 * | 5 | ["RRRBB","GGBBB","BBBRR","BBRRR","RRRRR"] | [4, 3] |
 */
class Solution {
    fun solution(picture: Array<String>): Array<Int> {
        val yCnt = picture.size
        val xCnt = picture[0].length

        val 지도 = MutableList(yCnt) { y -> picture[y].toMutableList() }
        val dx = mutableListOf(-1, 1, 0, 0)
        val dy = mutableListOf(0, 0, -1, 1)
        val 일반방문목록 = MutableList(yCnt) { BooleanArray(xCnt) }
        val 색약방문목록 = MutableList(yCnt) { BooleanArray(xCnt) }

        fun dfs(x: Int, y: Int, 방문목록: MutableList<BooleanArray>, 시작색: Char, 색약모드: Boolean) {
            방문목록[y][x] = true

            for (방향 in 0 until 4) {
                val nx = x + dx[방향]
                val ny = y + dy[방향]

                if (nx !in 0 until xCnt || ny !in 0 until yCnt) continue
                if (방문목록[ny][nx]) continue

                val 다음칸색 = 지도[ny][nx]

                if (!색약모드) {
                    if (다음칸색 != 시작색) continue
                } else {
                    if (시작색 == 'B') {
                        if (다음칸색 == 'R') continue
                        if (다음칸색 == 'G') continue
                    }
                    if (시작색 == 'R') {
                        if (다음칸색 == 'B') continue
                    }
                    if (시작색 == 'G') {
                        if (다음칸색 == 'B') continue
                    }
                }

                dfs(nx, ny, 방문목록, 시작색, 색약모드)
            }
        }

        var 일반구역수 = 0
        for (y in 0 until yCnt) {
            for (x in 0 until xCnt) {
                if (일반방문목록[y][x]) continue
                일반구역수++
                dfs(x, y, 일반방문목록, 지도[y][x], false)
            }
        }

        var 색약구역수 = 0
        for (y in 0 until yCnt) {
            for (x in 0 until xCnt) {
                if (색약방문목록[y][x]) continue
                색약구역수++
                dfs(x, y, 색약방문목록, 지도[y][x], true)
            }
        }

        return arrayOf(일반구역수, 색약구역수)
    }
}
