package backjoon.`20260210`



fun main() {
    val (N, M, K) = readln().split(" ").map { it.toInt() }
    val dr = intArrayOf(-1, -1, 0, 1, 1, 1, 0, -1)
    val dc = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1)
    data class Fireball(var r: Int, var c: Int, var m: Int, var s: Int, var d: Int)
    val fireballs = ArrayList<Fireball>(M)
    repeat(M) {
        val (r1, c1, m, s, d) = readln().split(" ").map { it.toInt() }
        fireballs.add(Fireball(r1 - 1, c1 - 1, m, s, d))
    }

    repeat(K) {
        val buckets = Array(N) { Array(N) { ArrayList<Fireball>() } }

        for (fb in fireballs) {
            val move = fb.s % N//순환구조
            var nr = fb.r + dr[fb.d] * move
            var nc = fb.c + dc[fb.d] * move

            nr = (nr % N + N) % N//순환
            nc = (nc % N + N) % N

            fb.r = nr//갱신
            fb.c = nc
            buckets[nr][nc].add(fb)
        }

        //합치고 나누기
        fireballs.clear()

        for (r in 0 until N) {
            for (c in 0 until N) {
                val cell = buckets[r][c]
                if (cell.isEmpty()) continue

                if (cell.size == 1) {
                    // 그대로 유지
                    fireballs.add(cell[0])
                    continue
                }

                //2개 이상
                var sumM = 0
                var sumS = 0
                var allEven = true
                var allOdd = true

                for (fb in cell) {
                    sumM += fb.m
                    sumS += fb.s
                    if (fb.d % 2 == 0) allOdd = false else allEven = false
                }

                val newM = sumM / 5
                if (newM == 0) continue  // 소멸

                val newS = sumS / cell.size
                val dirs = if (allEven || allOdd) intArrayOf(0, 2, 4, 6) else intArrayOf(1, 3, 5, 7)

                for (nd in dirs) {
                    fireballs.add(Fireball(r, c, newM, newS, nd))
                }
            }
        }
    }

    var sol = 0
    for (fb in fireballs) sol += fb.m
    print(sol)
}
