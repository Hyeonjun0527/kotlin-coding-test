package backjoon.`20260219`//  추월

fun main() {
    val n = readln().toInt()

    // 입구에서 들어온 순서
    val 입구목록 = MutableList(n) { "" }
    for (i in 0 until n) {
        입구목록[i] = readln()
    }

    // 차량번호 -> 입구에서의 위치(인덱스)
    val 입구인덱스 = HashMap<String, Int>()
    for (i in 0 until n) {
        입구인덱스[입구목록[i]] = i
    }

    // 입구에서 i번째 차가 이미 출구로 나갔는지
    val 나감 = BooleanArray(n)

    // "지금 나와야 정상인 차"의 입구 인덱스(아직 안 나온 것 중 맨 앞)
    var 기대인덱스 = 0

    var 추월한차량수 = 0

    for (i in 0 until n) {
        val 출구차량 = readln()
        val 나온인덱스 = 입구인덱스[출구차량] ?: 0

        // 기대인덱스를 "아직 안 나온 맨 앞"으로 계속 당겨오기
        while (기대인덱스 < n && 나감[기대인덱스]) {
            기대인덱스++
        }

        // 기대한 차가 아닌데 먼저 나왔다 => 반드시 추월한 차량
        if (나온인덱스 != 기대인덱스) {
            추월한차량수++
        }

        // 이 차는 나갔다 표시
        나감[나온인덱스] = true
    }

    println(추월한차량수)
}
