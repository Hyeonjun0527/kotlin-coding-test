package backjoon.`20260212` // CD (디버그 로그 버전)

fun main() {
    val sb = StringBuilder()
    var tc = 1

    while (true) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        if (n == 0 && m == 0) break

        val a = IntArray(n)
        val b = IntArray(m)

        for (idx in 0 until n) a[idx] = readln().toInt()
        for (idx in 0 until m) b[idx] = readln().toInt()

        var i = 0
        var j = 0
        var cnt = 0

        // ---- 시뮬레이션 로그 ----
        println("=== TC#$tc 시작 ===")
        println("a = ${a.joinToString(", ")}")
        println("b = ${b.joinToString(", ")}")
        println("i=0, j=0, cnt=0")
        // ------------------------

        var step = 1
        while (i < n && j < m) {
            val ai = a[i]
            val bj = b[j]

            // 현재 비교 상태 출력
            println("[STEP $step] i=$i a[i]=$ai | j=$j b[j]=$bj | cnt=$cnt")

            when {
                ai == bj -> {
                    cnt++
                    println("  -> 같음! cnt++ (cnt=$cnt), i++, j++")
                    i++; j++
                }
                ai < bj -> {
                    println("  -> a가 더 작음. i++")
                    i++
                }
                else -> {
                    println("  -> b가 더 작음. j++")
                    j++
                }
            }

            step++
        }

        println("=== TC#$tc 종료: 공통 개수 = $cnt ===")
        println()

        sb.append(cnt).append('\n')
        tc++
    }

    // 실제 정답 출력
    print(sb.toString())
}
