package backjoon.`20260210`//  지뢰 찾기

/*
[문제 요약]
- 실제 지뢰 배치(mineMap)와 사용자가 연 칸(map)이 주어진다.
- 열린 칸이 지뢰를 밟았으면 모든 지뢰를 보여준다.
- 열린 칸이 지뢰가 아니면 주변 8칸 지뢰 개수를 숫자로 표시한다.
- 닫힌 칸은 기본 '.' 유지.

[핵심 아이디어]
- 먼저 "폭발 여부(exploded)"를 한 번에 판정한다.
- 이후 전체 칸을 순회하면서:
  - 폭발 + 지뢰칸이면 '*'
  - 열린 칸이면 8방향 지뢰 개수 숫자
  - 나머지는 '.'

[데이터 구조]
- mineMap: 실제 지뢰 맵
- map: 사용자가 연 칸 맵
- sol: 최종 출력 맵
- dr/dc: 8방향 벡터

[시뮬레이션 순서]
<1> mineMap(정답판)과 map(열기 정보)을 입력받는다.
<2> 열린 칸 중 지뢰가 있는지 먼저 검사해 exploded를 확정한다.
<3> 전체 칸을 순회하며 출력 sol을 만든다.
    - exploded && 지뢰칸이면 '*'
    - 열린 칸이면 주변 8칸 지뢰 개수
    - 나머지는 '.'
<4> 완성된 sol을 줄 단위로 출력한다.

*/

fun main(args: Array<String>) {
    // [S0] 입력 로드
    // ----------------------------
    // 0) 입력
    // ----------------------------
    val n = readln().toInt() // [V5]
    var mineMap = mutableListOf<CharArray>() // [V0]
    repeat(n) {
        mineMap.add(readln().toCharArray())
    }
    var map = mutableListOf<CharArray>() // [V1]
    repeat(n) {
        map.add(readln().toCharArray())
    }
    val dr = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1) // [V4]
    var dc = arrayOf(-1, 0, 1, -1, 1, -1, 0, 1) // [V4]
    var sol = Array(n) { CharArray(n) { '.' } } // [V2]

    // ----------------------------
    // [S1] 폭발 여부 선판정
    // ----------------------------
    var exploded = false // [V3]
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (map[i][j] == 'x' && mineMap[i][j] == '*') {
                exploded = true
            }
        }
    }

    // ----------------------------
    // [S2] 출력 맵 계산 (전체 칸 순회)
    // ----------------------------
    for (i in 0 until n) {
        for (j in 0 until n) {

            // [S3] 폭발 + 지뢰칸 검사
            if (exploded && mineMap[i][j] == '*') {
                // [S4] 지뢰 표시
                sol[i][j] = '*'
                continue
            }

            // [S5] 열린 칸 검사
            if (map[i][j] == 'x') {
                var cnt = 0
                // [S6] 인접 8칸 지뢰 수 계산
                for (k in 0..7) {
                    val nr = i + dr[k]
                    val nc = j + dc[k]
                    if (nr !in 0 until n ||
                        nc !in 0 until n
                    ) continue
                    if (mineMap[nr][nc] == '*') {
                        cnt++
                    }
                }
                // [S7] 숫자 반영
                sol[i][j] = cnt.digitToChar()
            }
        }
    }

    // ----------------------------
    // [S8] 결과 출력
    // ----------------------------
    for (i in 0 until sol.size) {
        for (j in 0 until sol[0].size) {
            print(sol[i][j])
        }
        println()
    }
}
