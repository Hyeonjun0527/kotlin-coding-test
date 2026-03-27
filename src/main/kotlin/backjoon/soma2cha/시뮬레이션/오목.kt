package backjoon.`20260211`//  오목

/*
[문제 요약]
- 19x19 오목판에서 흑(1)/백(2) 중 승자가 있는지 판정한다.
- 승리 조건은 "정확히 5목"이며 6목 이상(장목)은 승리로 인정하지 않는다.
- 승자가 있으면 색과 좌표를 출력하고, 없으면 0을 출력한다.

[핵심 아이디어]
- 모든 칸을 시작점 후보로 보고 8방향으로 연속 길이를 검사한다.
- 중복 검출 방지를 위해 "이전 칸(반대방향)"이 같은 색이면 시작점으로 보지 않는다.
- 정확히 5개를 찾은 뒤 "다음 칸"이 같은 색인지 확인해 장목을 배제한다.

[데이터 구조]
- list: 19x19 보드
- dr/dc: 8방향 벡터
- sol: 승리 라인의 좌표 5개

[시뮬레이션 순서]
<1> 19x19 보드를 순회하며 시작점 후보를 잡는다.
<2> 시작점이 돌(1/2)이면 8방향을 검사한다.
<3> 반대방향 칸이 같은 색이면 중복 라인이므로 해당 방향은 스킵한다.
<4> 현재 방향으로 연속 돌 개수를 세고, 정확히 5개인지 확인한다.
<5> 5개라면 다음 칸이 같은 색인지 검사해 장목(6목 이상)을 배제한다.
<6> 승자를 찾으면 즉시 종료, 못 찾으면 0을 출력한다.

*/

fun main(args: Array<String>) {
    // [S0] 보드 입력
    // ----------------------------
    // 0) 입력
    // ----------------------------
    val list = Array(19) { // [V0]
        readln().split(" ").map { it.toInt() }
    }

    // 8방향 벡터
    val dr = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1) // [V1]
    val dc = arrayOf(-1, 0, 1, -1, 1, -1, 0, 1) // [V1]

    // 승자(0이면 없음)
    var 이긴주체 = 0 // [V2]

    data class 좌표(val i: Int, val j: Int)
    fun isRange(i: Int, j: Int) = (i in 0 until 19 && j in 0 until 19) // [V5]

    // 승리 라인 저장
    val sol = mutableListOf<좌표>() // [V3]

    // ----------------------------
    // 1) 전체 스캔
    // ----------------------------
    second@ for (i in 0 until 19) { // [S1]
        for (j in 0 until 19) {
            val 값 = list[i][j] // [V4]

            // [S2] 돌이 있는 칸만 검사
            if (값 != 0) {
                for (k in 0..7) { // [S3]

                    // [S4] 시작점 검증: 반대칸이 같은 색이면 중복 라인이므로 스킵
                    val prevI = i - dr[k]
                    val prevJ = j - dc[k]
                    if (isRange(prevI, prevJ) && list[prevI][prevJ] == 값) continue

                    sol.clear()
                    sol.add(좌표(i, j))
                    var newi = i
                    var newj = j
                    var 돌개수 = 1
                    for (t in 0..3) { // [S5] 연속 개수 세기
                        newi += dr[k]
                        newj += dc[k]
                        if (!isRange(newi, newj)) break
                        if (list[newi][newj] != 값) break
                        sol.add(좌표(newi, newj))
                        돌개수++
                    }

                    // [S6] 정확히 5개인지 확인
                    if (돌개수 == 5) {
                        var nextI = newi + dr[k]
                        var nextJ = newj + dc[k]
                        // [S7] 장목 배제: 다음 칸이 같은 색이면 실패
                        if (!isRange(nextI, nextJ) || list[nextI][nextJ] != list[i][j]) {
                            // [S8] 승자 확정
                            이긴주체 = 값
                            break@second
                        }
                    }
                }
            }
            // [S9] 승자 미확정이면 계속 스캔
        }
    }

    // ----------------------------
    // [S10] 결과 출력
    // 2) 결과 출력
    // ----------------------------
    println(이긴주체)

    if (이긴주체 != 0) {
        // 문제 조건: 가장 왼쪽, 그 다음 가장 위 좌표
        sol.sortWith(
            compareBy<좌표> { it.j }
                .thenBy { it.i }
        )
        println("${sol[0].i + 1} ${sol[0].j + 1}")
    }
}
