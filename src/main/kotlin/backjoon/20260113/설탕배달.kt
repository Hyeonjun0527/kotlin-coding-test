
fun main() {
    val n = readln().toInt()

    var 예측5봉지수 = n / 5//18이라고 하면 3이 됨.
    var 봉지개수 = 0
    while (예측5봉지수 != -1) {// 3 2 1 0
        var 남은설탕= n - 예측5봉지수 * 5
        if (남은설탕 % 3 == 0) {
            봉지개수 += 예측5봉지수 + 남은설탕 / 3
            break
        }
        예측5봉지수--
    }

    if (봉지개수 == 0) {
        println(-1)
    } else {
        println(봉지개수)
    }
}