package backjoon.`20260227`

/*
[문제 요약]
- 지뢰가 있는 칸(1)이 주어진 보드에서, 지뢰 자신과 주변 8칸은 모두 위험 지역이다.
- 위험 지역이 아닌 칸(안전 칸) 개수를 구한다.

[핵심 아이디어]
- 별도 보드(danger)를 만들어 위험 칸을 마킹한 뒤,
  마지막에 0인 칸만 세면 된다.
- 지뢰를 발견할 때마다 9칸(자기 자신 + 8방향)을 즉시 위험 처리한다.

[데이터 구조]
- board: 입력 보드
- 위험danger: 위험 마킹 보드(0/1)
- dx/dy: 9방향 벡터

[시뮬레이션 순서]
<1> danger 보드를 0으로 초기화한다.
<2> board를 순회하며 지뢰(1)를 찾는다.
<3> 지뢰를 찾을 때마다 자기 자신 포함 주변 9칸을 danger=1로 마킹한다.
<4> 순회가 끝나면 danger==0 칸을 세어 안전 칸 개수를 계산한다.
<5> 안전 칸 개수를 반환한다.

*/

class Solution6 {
    fun solution(board: Array<IntArray>): Int {
        // [S0] 입력 보드 로드
        // ----------------------------
        // 0) 입력 크기
        // ----------------------------
        val n = board.size // [V3]

        // [S1] danger 보드 초기화
        val 위험danger = MutableList(n) { MutableList(n) { 0 } } // [V1]

        // 8방향 + 자기자신(총 9칸)
        val dx = listOf(-1, -1, -1, 0, 0, 0, 1, 1, 1) // [V2]
        val dy = listOf(-1, 0, 1, -1, 0, 1, -1, 0, 1) // [V2]

        // ----------------------------
        // [S2] 지뢰 기준으로 위험 칸 마킹(전체 칸 순회)
        // ----------------------------
        for (x in 0 until n) {
            for (y in 0 until n) {
                // [S3] 현재 칸 지뢰 여부 검사
                if (board[x][y] == 1) {
                    // [S4] 주변 9칸 위험 마킹
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

        // ----------------------------
        // [S5] 순회 완료
        // [S6] 위험 아닌 칸(0) 세기
        // ----------------------------
        var 안전safe = 0 // [V4]
        for (x in 0 until n) {
            for (y in 0 until n) {
                if (위험danger[x][y] == 0) 안전safe++
            }
        }
        // [S7] 결과 반환
        return 안전safe
    }
}
