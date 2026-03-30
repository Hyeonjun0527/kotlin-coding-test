package backjoon.치트시트.실전문제모음.시뮬레이션

// 알고리즘 분류: 시뮬레이션

class Solution6 {
    fun solution(board: Array<IntArray>): Int {
        val n = board.size

        // 위험지역 표시용 (0=안전, 1=위험)
        val 위험danger = MutableList(n) { MutableList(n) { 0 } }

        // 8방향 + 자기자신(총 9칸)
        val dx = listOf(-1, -1, -1, 0, 0, 0, 1, 1, 1)
        val dy = listOf(-1, 0, 1, -1, 0, 1, -1, 0, 1)

        // 1) 지뢰를 찾으면 주변(자기 포함) 위험으로 체크
        for (x in 0 until n) {
            for (y in 0 until n) {
                if (board[x][y] == 1) {
                    for (k in 0 until 9) {
                        val nx = x + dx[k]
                        val ny = y + dy[k]
                        if (nx in 0 until n && ny in 0 until n) {
                            위험danger[nx][ny] = 1
                        }
                    }
                }
            }
        }

        // 2) 위험 아닌 칸(0) 세기
        var 안전safe = 0
        for (x in 0 until n) {
            for (y in 0 until n) {
                if (위험danger[x][y] == 0) 안전safe++
            }
        }
        return 안전safe
    }
}
