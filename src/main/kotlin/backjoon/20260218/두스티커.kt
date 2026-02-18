package backjoon.`20260218`

fun main() {
    val (H, W) = readln().split(" ").map { it.toInt() }
    val N = readln().toInt()

    val 스티커들 = mutableListOf<Pair<Int, Int>>()
    repeat(N) {
        val (r, c) = readln().split(" ").map { it.toInt() }
        스티커들.add(r to c)
    }

    var sol = 0

    for (i in 0 until N) {
        for (j in i + 1 until N) {
            val (r1, c1) = 스티커들[i]
            val (r2, c2) = 스티커들[j]

            val 후보1 = arrayOf(r1 to c1, c1 to r1)
            val 후보2 = arrayOf(r2 to c2, c2 to r2)

            for ((h1, w1) in 후보1) {
                for ((h2, w2) in 후보2) {
                    if (h1 > H || w1 > W || h2 > H || w2 > W) continue

                    val areaSum = h1 * w1 + h2 * w2

                    val 위아래가능 = (h1 + h2 <= H) && (w1 <= W) && (w2 <= W)

                    val 좌우가능 = (w1 + w2 <= W) && (h1 <= H) && (h2 <= H)

                    if (위아래가능 || 좌우가능) {
                        if (areaSum > sol) sol = areaSum
                    }
                }
            }
        }
    }

    println(sol)
}


/*
1. 스티커 모든 쌍(조합순서고려x 같은스티커 없게) 반복해야되고
2. 스티커는 회전가능하니 가로버전 세로버전 2가지가 존재함.
3. 두 스티커택인데 둘다 회전 가능하니 2가지 x 2가지 조합 존재함.
4.둘중 하나라도 종이보다 크면 바로 스킵
5.두 스티커 정해졌을때 그거를 위로쌓는식 아래로 쌓는식이 가능함.
6. 브루트포스로 max구함.

 */