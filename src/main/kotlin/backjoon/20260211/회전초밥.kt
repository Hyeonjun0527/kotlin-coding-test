package backjoon.`20260211`

/*
 * BOJ 15961 회전 초밥 (Kotlin)
 * - 입력: readln()만 사용
 * - 핵심: 슬라이딩 윈도우 + 카운팅(freq) + 원형 인덱스 (i + k) % N
 *
 * ⚠️ N 최대 3,000,000이라 Kotlin에서 readln()만 쓰면 입력이 병목이라 시간초과 가능성 있음.
 * (요구사항대로 로직/입력 방식은 그대로 유지)
 */

fun main() {
    //접시 종류 연속 쿠폰
    val (N, d, k, c) = readln().split(" ").map { it.toInt() }

    val 벨트 = IntArray(N)
    for (i in 0 until N) {
        벨트[i] = readln().toInt()
    }

    val freq = IntArray(d + 1)//카운트배열
    var 현재서로다른종류 = 0

    // 초기윈도우 0 .. k-1
    for (i in 0 until k) {
        val sushiType = 벨트[i]
        if (freq[sushiType] == 0) 현재서로다른종류++
        freq[sushiType]++
    }

    var 최대종류수best = 0

    //창 을 N번 밀면서 답 갱신
    for (startIdx in 0 until N) {
        //쿠폰 초밥이 창에 없으면 +1
        val 후보값candidate = 현재서로다른종류 + if (freq[c] == 0) 1 else 0
        if (후보값candidate > 최대종류수best) 최대종류수best = 후보값candidate

        //out: startIdx
        val 나가는초밥out = 벨트[startIdx]
        freq[나가는초밥out]--
        if (freq[나가는초밥out] == 0) 현재서로다른종류--

        //in: (startIdx + k) % N
        val 들어오는인덱스inIdx = (startIdx + k) % N
        val 들어오는초밥in = 벨트[들어오는인덱스inIdx]
        if (freq[들어오는초밥in] == 0) 현재서로다른종류++
        freq[들어오는초밥in]++
    }

    print(최대종류수best)
}