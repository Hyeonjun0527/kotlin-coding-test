package backjoon.`20260211`//  오목

fun main(args: Array<String>) {
    //여섯알 인정안해줌..검은색 vs 흰 누가 이겻냐. 다섯 바둑알 저장해야함
    //19x19 전부 순회 1 발견시. 한쪽방향으로 쭉 검사.
    //5줄 찾으면 그거 우승임. 리턴하고 그거 저장
    val list = Array(19) {
        readln().split(" ").map { it.toInt() }
    }
//    for (row in list) {
//        for (x in row) {
//            print("$x ")
//        }
//        println()
//    }
    //row 방향 첫줄 생각해보면 북서 북 북동 있겠지. 그러니 -1,-1,-1이 있는거고,
    //둘째 줄 생각해보면 0,0이지.
    //세번재줄은  1 1 1이지. 그럼 dc는 첫줄은-1,0,1, 두번째는 -1 1, 세번째는 -1,0,1임.
    val dr = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
    val dc = arrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

    //한방향 쭉가야함.

    var 이긴주체 = 0

    data class 좌표(val i: Int, val j: Int)
    fun isRange(i: Int, j: Int) = (i in 0 until 19 && j in 0 until 19)

    val sol = mutableListOf<좌표>()
    second@ for (i in 0 until 19) {
        for (j in 0 until 19) {
            //돌이있으면
            val 값 = list[i][j]

            //순회
            if (값 != 0) {
                for (k in 0..7) {

                    // 시작점 체크: 이전 칸(반대방향)이 같은 색이면 시작점이 아니므로 스킵
                    val prevI = i - dr[k]
                    val prevJ = j - dc[k]
                    if (isRange(prevI, prevJ) && list[prevI][prevJ] == 값) continue

                    sol.clear()
                    sol.add(좌표(i, j))
                    var newi = i
                    var newj = j
                    var 돌개수 = 1
                    for (t in 0..3) {
                        newi += dr[k]
                        newj += dc[k]
                        if (!isRange(newi, newj)) break
                        if (list[newi][newj] != 값) break
                        sol.add(좌표(newi, newj))
                        돌개수++
                    }

                    if (돌개수 == 5) {
                        var nextI = newi + dr[k]
                        var nextJ = newj + dc[k]
                        if (!isRange(nextI,nextJ) || list[nextI][nextJ] != list[i][j]) {
                            이긴주체 = 값
                            break@second
                        }
                    }
                }
            }
        }
    }//다돌음

    println(이긴주체)

    if (이긴주체 != 0) {
        sol.sortWith(
            compareBy<좌표> { it.j }
                .thenBy { it.i }
        )
        println("${sol[0].i + 1} ${sol[0].j + 1}")
    }

    // 가장왼쪽에 있는 바둑알. 즉 c 낮은 우선순위로, c낮으면
}