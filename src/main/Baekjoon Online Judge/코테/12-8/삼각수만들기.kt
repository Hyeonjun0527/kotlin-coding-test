fun main() {
    val n = readln().toInt()

    // 1) 삼각수 시퀀스 생성 (1, 3, 6, 10, 15, ...) sum 과 인덱스 i가 필요. 초기값 1 to 1
    val triangulars = generateSequence(1 to 1) { (sum, i) ->
        (sum + (i + 1)) to (i + 1)
    }.map { it.first }

    // 2) n보다 작은 삼각수들
    val below = triangulars.takeWhile { it < n }.toList()
    println("삼각수들: $below")

    // 3) n 이상이 되는 첫 삼각수 (upper)
    val upper = triangulars.first { it >= n }

    // 4) lower = n보다 작은 마지막 삼각수
    val lower = below.lastOrNull() ?: 0

    // 5) 인덱스(k) 계산
    val k = below.size        // lower = Tk
    val nextK = k + 1         // upper = T(k+1)

    println("n은 T$k($lower) 과 T$nextK($upper) 사이에 있습니다.")
}
