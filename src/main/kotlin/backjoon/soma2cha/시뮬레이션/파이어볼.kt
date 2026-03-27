package backjoon.`20260210`

/*
[문제 요약]
- N x N 격자 위에 M개의 파이어볼이 있다.
- 각 파이어볼은 (행, 열, 질량 m, 속력 s, 방향 d)을 가진다.
- 총 K번 명령을 반복한다.
  <1> 모든 파이어볼을 방향/속력만큼 이동(격자 밖으로 나가면 토러스처럼 반대편으로 연결)
  <2> 같은 칸 파이어볼들을 규칙대로 합치고 나눈다.
- 분리 규칙:
  - 1개면 그대로 유지
  - 2개 이상이면:
    새 질량 = 질량합 / 5
    새 속력 = 속력합 / 개수
    방향은 원래 방향들의 홀짝이 모두 같으면 [0,2,4,6], 아니면 [1,3,5,7]
    새 질량이 0이면 소멸
- 최종 출력: K번 후 남은 파이어볼 질량 합

[핵심 아이디어]
- "이동 단계"와 "합치기/분리 단계"를 반드시 분리해야 충돌 없이 구현 가능하다.
- 같은 칸에 모인 파이어볼을 한 번에 처리하기 위해 임시 버킷(grid of list)을 쓴다.

[데이터 구조]
- fireballs: 현재 라운드에 살아있는 파이어볼 목록
- buckets[r][c]: 이동이 끝난 뒤 (r,c)에 모인 파이어볼 목록

[시뮬레이션 순서(매 라운드)]
<1> 모든 파이어볼을 동시에 이동한 것으로 보고 buckets에 모은다.
<2> fireballs를 비우고, 각 칸(cell)을 순회한다.
<3> cell이 비면 패스, 1개면 그대로 유지.
<4> 2개 이상이면 질량/속력/방향홀짝을 집계해서 새 파이어볼 4개 생성 규칙 적용.
<5> 새 질량이 0이면 소멸 처리.
<6> K라운드 종료 후 남은 질량 합을 출력.

*/

fun main() {
    // [S0] 초기 입력/상태 준비
    // ----------------------------
    // 0) 입력 + 기본 설정
    // ----------------------------
    val (N, M, K) = readln().split(" ").map { it.toInt() } // [V4]
    // 8방향 벡터 (문제 방향 인덱스 순서)
    val dr = intArrayOf(-1, -1, 0, 1, 1, 1, 0, -1) // [V3]
    val dc = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1) // [V3]

    // 파이어볼 상태
    data class Fireball(var r: Int, var c: Int, var m: Int, var s: Int, var d: Int)

    // 현재 살아있는 파이어볼 목록
    val fireballs = ArrayList<Fireball>(M) // [V0]
    repeat(M) {
        val (r1, c1, m, s, d) = readln().split(" ").map { it.toInt() }
        // 입력은 1-index라 0-index로 변환
        fireballs.add(Fireball(r1 - 1, c1 - 1, m, s, d))
    }

    // ----------------------------
    // 1) K라운드 시뮬레이션
    // ----------------------------
    repeat(K) { // [S1] 라운드 시작, [V2]
        // 이동 결과를 담을 버킷
        val buckets = Array(N) { Array(N) { ArrayList<Fireball>() } } // [V1]

        // [S2] 이동 단계
        for (fb in fireballs) {
            // N칸 주기이므로 속력은 N으로 mod 가능
            val move = fb.s % N
            var nr = fb.r + dr[fb.d] * move
            var nc = fb.c + dc[fb.d] * move

            // 음수 보정 포함한 토러스 인덱싱
            nr = (nr % N + N) % N
            nc = (nc % N + N) % N

            // 파이어볼 위치 갱신 후 해당 칸 버킷에 적재
            fb.r = nr
            fb.c = nc
            buckets[nr][nc].add(fb)
        }

        // [S3] 재구성 준비
        fireballs.clear()

        // [S4] 각 칸 순회
        for (r in 0 until N) {
            for (c in 0 until N) {
                val cell = buckets[r][c]
                // [S5] 칸 비어있는지 검사
                if (cell.isEmpty()) continue

                if (cell.size == 1) {
                    // [S6] 1개면 상태 변화 없음
                    fireballs.add(cell[0])
                    continue
                }

                // [S7] 2개 이상이면 집계
                var sumM = 0
                var sumS = 0
                var allEven = true
                var allOdd = true

                for (fb in cell) {
                    sumM += fb.m
                    sumS += fb.s
                    if (fb.d % 2 == 0) allOdd = false else allEven = false
                }

                val newM = sumM / 5 // [S8] 새 질량 계산
                if (newM == 0) continue  // [S9] 질량 0 검사 -> [S10] 소멸

                val newS = sumS / cell.size
                val dirs = if (allEven || allOdd) intArrayOf(0, 2, 4, 6) else intArrayOf(1, 3, 5, 7)

                // [S11] 분리 생성
                for (nd in dirs) {
                    fireballs.add(Fireball(r, c, newM, newS, nd))
                }
            }
        }
        // [S12] 이번 라운드 상태 확정(다음 repeat로 진행)
    }

    // [S13] 최종 질량 합 출력
    // ----------------------------
    // 2) 최종 질량 합 출력
    // ----------------------------
    var sol = 0 // [V5]
    for (fb in fireballs) sol += fb.m
    print(sol)
}
