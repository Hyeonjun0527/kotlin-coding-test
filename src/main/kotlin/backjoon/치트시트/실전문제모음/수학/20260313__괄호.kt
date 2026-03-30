package backjoon.치트시트.실전문제모음.수학

// 알고리즘 분류: 수학

private const val 나머지 = 1_000_000_007L

fun main() {
    val 테스트케이스수 = readln().toInt()
    val 길이목록 = MutableList(테스트케이스수) { 0 }
    var 최대길이 = 0

    for (인덱스 in 0 until 테스트케이스수) {
        val 길이 = readln().toInt()
        길이목록[인덱스] = 길이
        최대길이 = maxOf(최대길이, 길이)
    }

    val 최대쌍수 = 최대길이 / 2
    val 경우의수 = LongArray(최대쌍수 + 1)
    경우의수[0] = 1L

    for (현재쌍수 in 1..최대쌍수) {
        var 누적합 = 0L

        for (안쪽쌍수 in 0 until 현재쌍수) {
            val 바깥뒤쪽쌍수 = 현재쌍수 - 1 - 안쪽쌍수
            누적합 = (누적합 + 경우의수[안쪽쌍수] * 경우의수[바깥뒤쪽쌍수]) % 나머지
        }

        경우의수[현재쌍수] = 누적합
    }

    val 정답출력 = StringBuilder()

    for (길이 in 길이목록) {
        if (길이 % 2 == 1) {
            정답출력.append(0).append('\n')
        } else {
            정답출력.append(경우의수[길이 / 2]).append('\n')
        }
    }

    print(정답출력)
}
