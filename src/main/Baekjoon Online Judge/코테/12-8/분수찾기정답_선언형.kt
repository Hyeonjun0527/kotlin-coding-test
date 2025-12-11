fun main() {
    val x = readln().toInt()

    // 무한 삼각수 시퀀스
    val triangulars = generateSequence(1 to 1) { (sum, i) ->
        (sum + (i + 1)) to (i + 1)
    }

    // x보다 작은 삼각수들
    val below = triangulars.takeWhile { it.first < x }.toList()

    // x 이상이 되는 첫 삼각수 = T(k+1)
    val upper = triangulars.first { it.first >= x }

    // k = below.size
    val k = below.last()     // lower = Tk
    val line = k.second + 1        // X는 (k+1)번째 대각선

    // offset 계산
    val offset = upper.first - x

    // 분자/분모 계산
    val (numer, denom) =
        if (line % 2 == 0) {
            // 짝수 줄: 분자 ↑ / 분모 ↓
            (1 + offset) to (line - offset)
        } else {
            // 홀수 줄: 분자 ↓ / 분모 ↑
            (line - offset) to (1 + offset)
        }

    println("$numer/$denom")
}
