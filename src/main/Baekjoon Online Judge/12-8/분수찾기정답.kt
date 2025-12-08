fun main() {
    val n = readln().toInt()

    // 1) n이 속한 대각선 i 찾기
    var i = 1
    var value = 1
    while (n > value) {
        i++
        value += i
    }

    // 2) 라인의 첫 번째 인덱스부터 몇 칸 떨어졌는지
    val offset = value - n  // 뒤에서부터 offset칸 떨어져 있음

    val numerator: Int//분자
    val denominator: Int//분모

    if (i % 2 == 0) {
        // 짝수 라인: 분자 ↑ / 분모 ↓
        numerator = 1 + offset
        denominator = i - offset
    } else {
        // 홀수 라인: 분자 ↓ / 분모 ↑
        numerator = i - offset
        denominator = 1 + offset
    }

    println("${numerator}/${denominator}")
}
