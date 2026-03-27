package backjoon.soma2cha.시뮬레이션

/*
[문제 요약]
- (type, mana) 구슬들을 왼쪽부터 하나씩 작업 공간(스택의 오른쪽)으로 넣는다.
- 구슬을 넣을 때마다 아래 규칙을 "변화가 없을 때까지" 반복한다.
  <1> 오른쪽 끝 두 구슬 타입이 같으면 병합 (마나는 합)
  <2> 오른쪽 끝 구슬 마나가 임계치 이상이면 발동
      - 발동 횟수 증가
      - 마나에서 임계치를 차감
      - 0이 되면 소멸
- 모든 구슬 처리 후 총 발동 횟수를 반환한다.

[핵심 아이디어]
- 작업 공간을 스택으로 본다. 오른쪽 끝만 바뀌므로 스택 top만 보면 된다.
- 병합/발동이 연쇄적으로 이어질 수 있으므로 "안정화 루프"를 돌린다.
- 발동은 1회씩 빼지 않고, 한 번에 k = mana / threshold 만큼 처리하면 빠르다.

[데이터 구조]
- thr[type] = threshold: 타입별 임계치 해시맵
- typeStack, manaStack: 작업 공간 스택
- size: 스택 크기 포인터
- activations: 총 발동 횟수(Long)

[시뮬레이션 순서(구슬 1개 처리 단위)]
<1> 현재 구슬을 스택 오른쪽(top)에 push한다.
<2> top 두 개 타입이 같으면 병합한다. (가능한 동안 반복)
<3> top 마나가 임계치 이상이면 발동한다.
    - k = mana / threshold 만큼 한 번에 카운트 증가
    - 남은 마나가 0이면 소멸(pop), 아니면 top 마나 갱신
    - 발동 가능한 동안 반복
<4> 2~3을 더 이상 변화가 없을 때까지 반복해 안정화한다.
<5> 모든 구슬 처리 후 activations를 반환한다.

*/

class Solution {
    fun solution(orbs: Array<Array<Any>>, thresholds: Array<String>): Long {
        // [S0] 초기화
        val 입력구슬들 = orbs // [V5]

        // ----------------------------
        // 0) 임계치 파싱
        // ----------------------------
        val thr = HashMap<String, Long>(thresholds.size * 2) // [V0]
        for (s in thresholds) {
            val p = s.indexOf(' ')
            val type = s.substring(0, p)
            val t = s.substring(p + 1).toLong()
            thr[type] = t
        }

        // ----------------------------
        // 1) 작업 공간 스택
        // ----------------------------
        val n = orbs.size
        val typeStack = arrayOfNulls<String>(n) // [V1]
        val manaStack = LongArray(n) // [V2]
        var size = 0 // [V3]

        var activations = 0L // [V4]

        // ----------------------------
        // 2) 구슬을 왼쪽 -> 오른쪽 순서로 처리
        // ----------------------------
        for (orb in 입력구슬들) {
            // [S1] 다음 구슬 읽기
            val type = orb[0] as String
            val mana = (orb[1] as Number).toLong()

            // [S2] 현재 구슬 push
            typeStack[size] = type
            manaStack[size] = mana
            size++

            // [S3] 안정화 루프 시작
            while (true) {
                var changed = false

                // [S4] top 두 개 타입이 같으면 병합 가능 검사
                while (size >= 2 && typeStack[size - 1] == typeStack[size - 2]) {
                    // [S5] 병합 수행
                    manaStack[size - 2] += manaStack[size - 1]
                    size--
                    changed = true
                }

                // [S6] top 발동 가능 검사
                while (size > 0) {
                    val topType = typeStack[size - 1]!!
                    val t = thr[topType]!! // 문제 조건상 항상 존재
                    val m = manaStack[size - 1]

                    if (m < t) break

                    // [S7] 발동 처리(한 번에 k회)
                    val k = m / t
                    activations += k
                    val remain = m % t

                    // [S8] remain 검사
                    if (remain == 0L) {
                        // [S9] 0이면 소멸(pop)
                        size--
                    } else {
                        // [S10] 남으면 top 마나 갱신
                        manaStack[size - 1] = remain
                    }
                    changed = true
                }

                // [S11] changed 검사
                if (!changed) break
            }
            // [S12] 다음 입력 구슬로 진행(for-loop가 담당)
        }

        // ----------------------------
        // [S_END] 총 발동 횟수 반환
        // 3) 총 발동 횟수 반환
        // ----------------------------
        return activations
    }
}
